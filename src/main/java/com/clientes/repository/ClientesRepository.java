package com.clientes.repository;

import com.clientes.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;


@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c order by c.idcliente desc")
    Page<Cliente> clientesPaginados(Pageable pageable);

    @Query("select AVG(c.edad) as promedio from Cliente c")
    BigDecimal obtenerPromedioEdades();

    @Query("select STDDEV(c.edad) as desviacionEstandar from Cliente c")
    BigDecimal obtenerDesviacionEstandarDeEdades();

    @Query("select count(*) from Cliente")
    BigInteger cantidadClientesRegistrados();


}