package com.clientes;

import com.clientes.common.mapeos.MapeoClientes;
import com.clientes.model.dto.ClienteDTO;
import com.clientes.model.dto.ClienteUpdateDTO;
import com.clientes.model.dto.ResponseWS;
import com.clientes.model.entity.Cliente;
import com.clientes.repository.ClientesRepository;
import com.clientes.service.ClienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceClienteApplicationTests {
    @Mock
    private ClientesRepository repository;
    @Mock
    private MapeoClientes mapeoClientes;
    @Autowired
    private ClienteService clienteService;
    private static  int idclientecreado;
    private static  int idclienteEliminar;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void testCrearCliente() {
        ClienteDTO clienteDTO = ClienteDTO.builder().nombre("Juan").apellido("Perez").edad(25).fechanacimiento("03/05/2000").build();
        Cliente clienteEntity = new Cliente(1, "Juan", "Perez", 30,LocalDate.now());
        Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(clienteEntity);
        ClienteDTO result = clienteService.crear(clienteDTO);
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        idclientecreado = result.getIdcliente();
    }

    @Test
    @Order(2)
    void testActualizarCliente() {
        idclienteEliminar = idclientecreado;
        ClienteUpdateDTO clienteUpdateDTO = new ClienteUpdateDTO();
        clienteUpdateDTO.setNombre("Carlos");
        clienteUpdateDTO.setApellido("Lopez");

        Cliente clienteExistente = new Cliente();
        clienteExistente.setIdcliente(idclientecreado);
        clienteExistente.setNombre("Juan");
        clienteExistente.setApellido("Perez");

        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setIdcliente(idclientecreado);
        clienteActualizado.setNombre("Carlos");
        clienteActualizado.setApellido("Lopez");

        when(repository.findById(idclientecreado)).thenReturn(Optional.of(clienteExistente));
        when(mapeoClientes.updateEntityCliente(clienteUpdateDTO, clienteExistente)).thenReturn(clienteActualizado);
        when(repository.save(clienteActualizado)).thenReturn(clienteActualizado);
        when(mapeoClientes.convertirClienteDTO(clienteActualizado)).thenReturn(new ClienteDTO());
        ClienteDTO result = clienteService.actualizar(idclientecreado, clienteUpdateDTO);

        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @Order(3)
    void testEliminarCliente() {
        Cliente clienteExistente = new Cliente(idclienteEliminar, "Juan", "Perez", 30, LocalDate.of(1995, 12, 10));
        Mockito.when(repository.findById(idclienteEliminar)).thenReturn(Optional.of(clienteExistente));
        ResponseWS response = clienteService.eliminar(idclienteEliminar);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Cliente eliminado", response.getMensaje());
    }


}
