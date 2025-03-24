package com.clientes.service;

import com.clientes.common.Utils.EventualidadesUtils;
import com.clientes.common.Utils.ListUtils;
import com.clientes.common.mapeos.MapeoClientes;
import com.clientes.model.dto.ClienteMetricasDTO;
import com.clientes.model.dto.EventualidadesClientesDTO;
import com.clientes.model.entity.Cliente;
import com.clientes.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetricasClientesService {
    @Autowired
    ClientesRepository repository;
    @Autowired
    MapeoClientes mapeoClientes;
    EventualidadesUtils utils = new EventualidadesUtils();
    public ClienteMetricasDTO metricas(){
        BigDecimal promedio = repository.obtenerPromedioEdades();
        BigDecimal desviacionStandar = repository.obtenerDesviacionEstandarDeEdades();
        BigInteger cantidadRegistrado = repository.cantidadClientesRegistrados();

        return ClienteMetricasDTO.builder()
                .promedio(promedio)
                .desviacionEstandarDeEdades(desviacionStandar)
                .clientesregistrados(cantidadRegistrado)
                .comentario("Todos los calculos que se muestran, se han hecho en base a la cantidad de clientes registrados. " +
                        "La cantidad de clientes registrados es: "+cantidadRegistrado).build();
    }

    public List<EventualidadesClientesDTO> eventualidadesClientes(){
        List<Cliente> listClientes = repository.findAll();
        List<EventualidadesClientesDTO> lstRetorno = new ArrayList<>();
        if(ListUtils.isNotEmpty(listClientes)){
            lstRetorno = mapeoClientes.cEventualidadesClientesDtos(listClientes);
        }
        lstRetorno.forEach(data -> {
            if(data != null){
                data.setDiasVividosDesdeSuNacimiento(utils.diasVividos(LocalDate.parse(data.getFechanacimiento())));
                data.setEdadExactaSegunFechaNacimiento(utils.calcularEdadExacta(LocalDate.parse(data.getFechanacimiento())));
                data.setFechaAproximadaFallecimiento(utils.calcularFechaFallecimiento(LocalDate.parse(data.getFechanacimiento())));
                data.setFechaAproximadaJubulacion(utils.calcularFechaJubilacion(LocalDate.parse(data.getFechanacimiento())));
                data.setFechaAproximadaProximoCumple(utils.proximoCumple(LocalDate.parse(data.getFechanacimiento())));
            }
        });
        return lstRetorno;
    }


}
