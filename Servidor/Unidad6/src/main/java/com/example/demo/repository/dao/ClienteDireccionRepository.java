package com.example.demo.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.ClienteDireccion;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClienteDireccionRepository extends JpaRepository<ClienteDireccion, Long> {

    @Query("SELECT c FROM ClienteDireccion c WHERE c.cliente.id = :idCliente AND c.direccion.id = :idDireccion")
    Optional<ClienteDireccion> findByClienteIdAndDireccionId(@Param("idCliente") Long idCliente, 
                                                             @Param("idDireccion") Long idDireccion);

    @Query("SELECT c FROM ClienteDireccion c WHERE c.cliente.id = :idCliente")
    List<ClienteDireccion> findByClienteId(@Param("idCliente") Long idCliente);
}
