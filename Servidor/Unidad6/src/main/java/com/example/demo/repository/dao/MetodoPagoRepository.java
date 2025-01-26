package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.MetodoPago;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    @Query("SELECT m FROM MetodoPago m WHERE m.tipo = :tipo")
    MetodoPago findByTipo(@Param("tipo") String tipo);
}
