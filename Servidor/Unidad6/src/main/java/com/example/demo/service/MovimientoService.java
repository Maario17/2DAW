package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.CuentaDTO;
import com.example.demo.model.dto.MovimientoDTO;

public interface MovimientoService {

	List<MovimientoDTO> findAll(CuentaDTO cuentaDTO);

    MovimientoDTO findById(Long id);

    void save(MovimientoDTO movimientoDTO);

    void deleteById(Long id);
	
}
