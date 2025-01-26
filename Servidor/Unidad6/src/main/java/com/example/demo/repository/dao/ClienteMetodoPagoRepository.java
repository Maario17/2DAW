package com.example.demo.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.ClienteMetodoPago;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface ClienteMetodoPagoRepository extends JpaRepository<ClienteMetodoPago, Long> {

    @Query("SELECT cmp FROM ClienteMetodoPago cmp WHERE cmp.cliente.id = :idCliente AND cmp.metodoPago.id = :idMetodoPago")
    Optional<ClienteMetodoPago> findByClienteIdAndMetodoPagoId(@Param("idCliente") Long idCliente,
                                                               @Param("idMetodoPago") Long idMetodoPago);

    @Query("SELECT cmp FROM ClienteMetodoPago cmp WHERE cmp.cliente.id = :idCliente")
    List<ClienteMetodoPago> findByClienteId(@Param("idCliente") Long idCliente);
}
