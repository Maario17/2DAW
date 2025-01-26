package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	@Query("SELECT m FROM Movimiento m WHERE m.cuentaOrigen.id = :cuentaId")
    List<Movimiento> findAllByCuentaOrigenId(@Param("cuentaId") Long cuentaId);
}

