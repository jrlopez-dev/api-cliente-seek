package com.clientes.service;

import com.clientes.common.mapeos.MapeoClientes;
import com.clientes.model.dto.*;
import com.clientes.model.entity.Cliente;
import com.clientes.repository.ClientesRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Service
public class ClienteService {
    @Autowired
    ClientesRepository repository;
    @Autowired
    MapeoClientes mapeoClientes;

    public ClienteDTO crear(ClienteDTO data){
        Cliente cliente = mapeoClientes.convertirClienteEntity(data);
        repository.save(cliente);
        return mapeoClientes.convertirClienteDTO(cliente);
    }

    public ClienteDTO actualizar(Integer id, ClienteUpdateDTO data){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontro cliente con id: "+id));
        Cliente clienteActualizado = mapeoClientes.updateEntityCliente(data, cliente);
        repository.save(clienteActualizado);
        return mapeoClientes.convertirClienteDTO(clienteActualizado);
    }

    public ResponseWS eliminar(Integer id){
        Cliente cliente = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("No se encontro cliente con id: "+id));
        repository.delete(cliente);
        return ResponseWS.builder()
                .status(200)
                .mensaje("Cliente eliminado")
                .timestap(new Date()).build();
    }

    public Paginacion<ClienteDTO> obtenerClientes(Integer pagina, Integer size){
        Pageable pagin = (Pageable) PageRequest.of(pagina,size);
        Page<Cliente> pageClientes = repository.clientesPaginados(pagin);
        if(pageClientes == null){
            throw new ServiceException("No se obtuvieron resultados");
        }
        Paginacion<ClienteDTO> retorno = new Paginacion<>();
        retorno.setData(mapeoClientes.convertirClienteDTOList(pageClientes.getContent()));
        retorno.setPaginaActual(pageClientes.getNumber());
        retorno.setTotalElementos(pageClientes.getTotalElements());
        retorno.setTotalPaginas(pageClientes.getTotalPages());

        return retorno;
    }

    public ClienteDTO buscarClientePorId(Integer id){
        if(id ==null) throw new ServiceException("El id es requerido");
        Cliente cliente = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("No se encontro cliente con id: "+id));
        return mapeoClientes.convertirClienteDTO(cliente);
    }

    void validarformatofechas(LocalDate date){
        String fecha = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha "+fecha+", no contiene el formato correcto. El formato debe ser: dd/MM/yyyy");
        }
    }

}
