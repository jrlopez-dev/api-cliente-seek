package com.clientes.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Tag(name = "ClienteDTO", description = "Esta DTO sirve para crear un nuevo cliente")
public class ClienteDTO implements Serializable {
    private Integer idcliente;
    @NotBlank(message = "El campo nombre es requerido")
    @Schema(description = "Nombre del cliente")
    private String nombre;
    @NotBlank(message = "El campo apellido es obligatorio")
    @Schema(description = "Apellido del cliente")
    private String apellido;
    @NotNull(message = "El campo edad es obligatorio")
    @Schema(description = "Edad del cliente")
    private Integer edad;
    @NotBlank(message = "El campo fechanacimiento es requerido")
    @Schema(description = "Fecha de nacimiento del cliente. Debe Formato. dd/MM/yyyy y sin comillas. Ejemplo 03/21/2024")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
            message = "El formato de fecha debe ser dd/MM/yyyy"
    )
    private String fechanacimiento;

}
