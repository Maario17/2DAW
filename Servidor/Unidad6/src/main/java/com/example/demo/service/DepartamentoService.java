package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.DepartamentoDTO;
import com.example.demo.model.dto.ClienteDepartamentoDTO;

public interface DepartamentoService {

    public List<ClienteDepartamentoDTO> findAllByCliente(Long idCliente);

    public DepartamentoDTO findById(Long id);

    public void save(DepartamentoDTO departamentoDTO, Long idCliente);

    public void deleteById(Long id);

}
