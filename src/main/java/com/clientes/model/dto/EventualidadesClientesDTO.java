package com.clientes.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventualidadesClientesDTO {
    @Schema(description = "Nombre del cliente")
    private String nombre;
    @Schema(description = "Apellido del cliente")
    private String apellido;
    @Schema(description = "Edad del cliente")
    private Integer edad;
    @NotBlank(message = "El campo fechanacimiento es requerido")
    @Schema(description = "Fecha de nacimiento del cliente. Debe Formato. dd/MM/yyyy y sin comillas. Ejemplo 03/21/2024")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
            message = "El formato de fecha debe ser dd/MM/yyyy"
    )
    private String fechanacimiento;
    @Schema(description = "Fecha aproximada de jubilacion calculada segun su fecha de nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/El_Salvador")
    private LocalDate fechaAproximadaJubulacion;
    @Schema(description = "Fecha aproximada de jubilacion, calculada segun su fecha de nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/El_Salvador")
    private Integer edadExactaSegunFechaNacimiento;
    @Schema(description = "Fecha aproximada de fallecimiento, calculada segun su fecha de nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/El_Salvador")
    private LocalDate fechaAproximadaFallecimiento;
    @Schema(description = "Fecha aproximada de su proximo cumpleaño, calculada segun su fecha de nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/El_Salvador")
    private LocalDate fechaAproximadaProximoCumple;
    @Schema(description = "Dias vividos hasta la fecha actual, calculada segun su fecha de nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/El_Salvador")
    private Long diasVividosDesdeSuNacimiento;
}
