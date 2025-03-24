package com.clientes.controller;

import com.clientes.model.dto.ClienteMetricasDTO;
import com.clientes.model.dto.EventualidadesClientesDTO;
import com.clientes.service.MetricasClientesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType; // Importa MediaType
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Metricas", description = "Endpoint expuestos para consumir el servicio de clientes ver " +
        "metricas y datos adicionales calulados como eventualidades futuras")
@Validated
@RestController
@RequestMapping("/eventualidades")
public class ClientesMetricasController {
    @Autowired
    private MetricasClientesService serviceMetricas;

    @GetMapping(value = "/reporte", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Endpoint que retorna un objeto con datos importantes de clientes, promedio de edades, desviacion estandar de edades, cantidad de registros etc.")
    public ResponseEntity<ClienteMetricasDTO> obtenerMetricas() {
        return ResponseEntity.ok(serviceMetricas.metricas());
    }

    @GetMapping("/evantualidadesFuturas")
    @Operation(summary = "Endpoint que retorna un objeto con datos de clientes y con calculos de eventualidades futuras segun su fecha de nacimiento")
    public ResponseEntity<List<EventualidadesClientesDTO>> obtenerEventualidades() {
        return ResponseEntity.ok(serviceMetricas.eventualidadesClientes());
    }
}
