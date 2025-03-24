FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

RUN addgroup --system appgroup && adduser --system app --ingroup appgroup

RUN chown -R app:appgroup /app

USER app
COPY --chown=app:appgroup . .

RUN mkdir -p /app/target && chmod -R 777 /app/target

RUN mvn clean package -DskipTests --batch-mode -q

# Etapa final (Runtime)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

RUN addgroup -g 10001 appgroup && adduser -D -u 10001 -G appgroup app

COPY --from=builder /app/target/*.jar /app/app.jar
RUN chown -R app:appgroup /app

USER app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
