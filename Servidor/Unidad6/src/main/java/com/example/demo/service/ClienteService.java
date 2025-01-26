package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClienteDTO;


public interface ClienteService {

	public List<ClienteDTO> findAll();

    public ClienteDTO findById(Long id);

    public void save(ClienteDTO clienteDTO);

    public void deleteById(Long id);
}