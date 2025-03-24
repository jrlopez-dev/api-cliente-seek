SERVICE CLIENTE

Este servicio esta desarrollado con el framework de spring boot bajo la arquitectura de microservicio con la version de JAVA 17 el cual tiene como unica funcion poder crear, actualizar, eliminar y buscar clientes.

Ademas de esos lleva dependencias para el monitoreo como actuactor y prometheus y seguridad con token. Keycloak esta integrado para este servicio como proveedor de identidad token. El flujo principal de este servicio es que el usario inicialmente solicita un token,
el cual ese token es enviado en el header de las peticiones y es validado si el token es valido en el servicio de keycloak. 

En la coleccion de POSTMAN va un endpoint el cual sirve para solicitar ese token y en automatico se lo setea a una variable de la coleccion para poder tenerlo disponible para cada peticion.

El servicio lleva dos bloques de endpoint uno llamado como METRICAS el cual son recursos expuestos con informacion de clientes creada con un fin informatico el cual no necesitan de un token, y el segundo bloque llamado CLIENTES el cual estan
 todos los endpoint expuestos para el CRUD de clientes el cual si requieren de autenticacion con token.

 En la doumentacion de Swagger les he ampliado la documentacion.

 Esta es la url del servicio desplegado en GOOCLE CLOUD RUN: https://service-cliente-seek-976877520928.us-central1.run.app/swagger-ui/index.html

 Y este es el servidor de keycloak utilizado para la autenticacion: https://keycloakseek-976877520928.us-central1.run.app
