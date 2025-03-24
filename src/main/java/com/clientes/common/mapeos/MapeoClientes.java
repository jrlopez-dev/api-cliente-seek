package com.clientes.common.mapeos;

import com.clientes.model.dto.ClienteDTO;
import com.clientes.model.dto.ClienteUpdateDTO;
import com.clientes.model.dto.EventualidadesClientesDTO;
import com.clientes.model.entity.Cliente;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapeoClientes {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ClienteDTO convertirClienteDTO(Cliente entity);

    @Mapping(source = "fechanacimiento", target = "fechanacimiento", qualifiedByName = "mapStringToFecha")
    @Mapping(target = "idcliente", ignore = true)
    Cliente convertirClienteEntity(ClienteDTO dto);
    List<ClienteDTO> convertirClienteDTOList(List<Cliente> clienteList);
    List<Cliente> convertirClienteEntityList(List<ClienteDTO> clienteDTOList);
    @Mapping(source = "fechanacimiento", target = "fechanacimiento", qualifiedByName = "mapStringToFecha")
    Cliente updateEntityCliente(ClienteUpdateDTO dto, @MappingTarget Cliente entity);

    EventualidadesClientesDTO convertirDTO(Cliente entity);

    List<EventualidadesClientesDTO> cEventualidadesClientesDtos(List<Cliente> clienteList);

    @Named("mapStringToFecha")
    static LocalDate mapStringToFecha(String fecha) {
        return (fecha != null && !fecha.isEmpty()) ? LocalDate.parse(fecha, FORMATTER) : null;
    }

}
