package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Direccion;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

	@Query(value = "SELECT A.* FROM direcciones A JOIN clientesdirecciones B "
	        + "ON A.ID = B.iddireccion WHERE B.idcliente = :idcliente", nativeQuery = true)
	public List<Direccion> findAllByCliente(@Param("idcliente") Long idCliente);

}