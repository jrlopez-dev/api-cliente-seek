package com.clientes.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClienteMetricasDTO {
    @Schema(description = "Promedio de edades, calculo registrado segun la cantidad de clientes registrados")
    private BigDecimal promedio;
    @Schema(description = "Desviacion estandar de edades, calculo registrado con los datos de edades y segun la cantidad de clientes registrados")
    private BigDecimal desviacionEstandarDeEdades;
    @Schema(description = "Cantidad de clientes registrados")
    private BigInteger clientesregistrados;
    @Schema(description = "Comentario o nota que se coloca en caso sea necesario")
    private String comentario;
}
