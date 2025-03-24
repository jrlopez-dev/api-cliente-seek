package com.clientes.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Paginacion<T> {
    List<T> data;
    Integer paginaActual;
    Long totalElementos;
    Integer totalPaginas;
}
