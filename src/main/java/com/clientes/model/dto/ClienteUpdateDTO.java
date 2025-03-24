package com.clientes.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteUpdateDTO {
    @Schema(description = "Nombre del cliente")
    private String nombre;
    @Schema(description = "Apellido del cliente")
    private String apellido;
    @Schema(description = "Edad del cliente")
    private Integer edad;
    @Schema(description = "Fecha de nacimiento del cliente. Debe Formato. dd/MM/yyyy y sin comillas. Ejemplo 03/21/2024")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
            message = "El formato de fecha debe ser dd/MM/yyyy"
    )
    private String fechanacimiento;

}
