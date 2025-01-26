package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.CuentaDTO;

public interface CuentaService {

	public List<CuentaDTO> findAllByCliente(Long idCliente);

    public CuentaDTO findById(Long id);

    public void save(CuentaDTO cuentaDTO, Long idCliente);

    public void deleteById(Long id);
    
    public List<CuentaDTO> findAll();
}
