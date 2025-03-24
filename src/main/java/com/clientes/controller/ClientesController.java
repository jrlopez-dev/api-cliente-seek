package com.clientes.controller;

import com.clientes.model.dto.*;
import com.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Clientes", description = "Endpoint expuestos para consumir el servicio de clientes y realizar CRUD")
@Validated
@RestController
@RequestMapping("/clientes")
public class ClientesController {
    @Autowired
    private ClienteService service;


    @PostMapping("/crear")
    @Operation(summary = "Recurso que recibe un DTO en formato JSON, con la data requerida para crear un nuevo cliente")
    public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Recurso que recibe un DTO en formato JSON, con la data requerida para actualizar un cliente")
    public ResponseEntity<ClienteDTO> update(@Valid @PathVariable(value = "id", required = true) Integer id,
                                             @Valid @RequestBody ClienteUpdateDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Recurso que recibe un id de cliente para procesar su eliminacion")
    public ResponseEntity<ResponseWS> delete(@PathVariable(value = "id", required = true) Integer id) {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Recurso que recibe un id de parametro y devuelve un objeto de tipo cliente")
    public ResponseEntity<ClienteDTO>  getById(@Valid @PathVariable(value = "id", required = true) Integer id) {
        return ResponseEntity.ok(service.buscarClientePorId(id));
    }

    @GetMapping("/gatAllClientes")
    @Operation(summary = "Recurso que retorna todos los clientes registrados, de forma paginada")
    public ResponseEntity<Paginacion<ClienteDTO>> getALL(@Valid @RequestParam(value = "pagina", defaultValue = "0" ,required = true) Integer pagina,
                                                         @RequestParam(value = "size", defaultValue = "10", required = true) Integer size) {
       return ResponseEntity.ok(service.obtenerClientes(pagina, size));
    }

}
