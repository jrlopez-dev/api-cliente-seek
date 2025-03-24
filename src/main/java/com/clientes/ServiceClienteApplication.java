package com.clientes;

import com.clientes.common.Utils.DateUtils;
import com.clientes.model.entity.Cliente;
import com.clientes.repository.ClientesRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SERVICIO PARA LA GESTION DE CLIENTES", version = "1.0",
        description = "Servicio que gestiona la persistencia de clientes en la base de datos.\n\n"
                + "Proporciona recursos para crear, actualizar, eliminar y buscar clientes,\n"
                + "brindando un servicio eficiente de gestión de datos.\n\n"
                + "Al iniciar el servicio por primera vez, se carga automáticamente una lista\n"
                + "inicial de 10 clientes, facilitando la realización de consultas y pruebas. \n\n" +
                "Tiene dos apartados de Endpoint donde: \n\n CLIENTES estan expuestos los recursos para el CRUD de clientes " +
                "\n\n METRICAS es el apartado de Endpoint donde se muestran datos de analisis de los clientes donde se han hecho " +
                "funionalidades jugando con los datos de los clientes, como calcular el promedio de edades, calculos de desviacion estandar, hacer calculos de eventos futuros" +
                ", como por ejemplo calcular su fecha de jubilacion y su fecha de su falleciomiento en casos hipoteticos. \n\nTodo esto jugando con " +
                "el dato de fecha de nacimiento del cliente. \n\nPara el calculo de jubilacion se ha conciderado para hombres y mujeres un parametro de jubilaion" +
                " a los 65 años y para el calculo de la fecha de fallecimiento se ha calculado tomando de base una esperanza de vida de 80 años. "))
public class ServiceClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceClienteApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ClientesRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Cliente> clients = Arrays.asList(
                        Cliente.builder().nombre("Jonathan").apellido("Lopez").edad(32).fechanacimiento(LocalDate.now().minusYears(32)).build(),
                        Cliente.builder().nombre("Maria").apellido("Lopez").edad(22).fechanacimiento(LocalDate.now().minusYears(22)).build(),
                        Cliente.builder().nombre("Carlos").apellido("Dias").edad(12).fechanacimiento(LocalDate.now().minusYears(12)).build(),
                        Cliente.builder().nombre("Ana").apellido("Martines").edad(17).fechanacimiento(LocalDate.now().minusYears(17)).build(),
                        Cliente.builder().nombre("Genesis").apellido("Dueñas").edad(28).fechanacimiento(LocalDate.now().minusYears(28)).build(),
                        Cliente.builder().nombre("Elena").apellido("Gomez").edad(30).fechanacimiento(LocalDate.now().minusYears(30)).build(),
                        Cliente.builder().nombre("Juan").apellido("Perez").edad(31).fechanacimiento(LocalDate.now().minusYears(31)).build(),
                        Cliente.builder().nombre("Brenda").apellido("Dueñas").edad(25).fechanacimiento(LocalDate.now().minusYears(25)).build(),
                        Cliente.builder().nombre("Brayan").apellido("Gomez").edad(16).fechanacimiento(LocalDate.now().minusYears(16)).build(),
                        Cliente.builder().nombre("Liset").apellido("Martinez").edad(20).fechanacimiento(LocalDate.now().minusYears(20)).build()
                );
                repository.saveAll(clients);
            }
        };
    }
}
