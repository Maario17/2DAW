package com.example.demo.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.ClienteDepartamento;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface ClienteDepartamentoRepository extends JpaRepository<ClienteDepartamento, Long> {

    @Query("SELECT cd FROM ClienteDepartamento cd WHERE cd.cliente.id = :idCliente AND cd.departamento.id = :idDepartamento")
    Optional<ClienteDepartamento> findByClienteIdAndDepartamentoId(@Param("idCliente") Long idCliente,
                                                                   @Param("idDepartamento") Long idDepartamento);

    @Query("SELECT cd FROM ClienteDepartamento cd WHERE cd.cliente.id = :idCliente")
    List<ClienteDepartamento> findByClienteId(@Param("idCliente") Long idCliente);
}
