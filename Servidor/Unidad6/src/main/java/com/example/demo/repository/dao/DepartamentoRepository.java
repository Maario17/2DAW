package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Departamento;

import jakarta.transaction.Transactional;


@Transactional
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	@Query(value = "SELECT D.* FROM Departamento D "
            + "JOIN ClienteDepartamento C ON D.id = C.id_departamento "
            + "WHERE C.id_cliente = :idcliente", nativeQuery = true)
public List<Departamento> findAllByCliente(@Param("idcliente") Long idCliente);

}
