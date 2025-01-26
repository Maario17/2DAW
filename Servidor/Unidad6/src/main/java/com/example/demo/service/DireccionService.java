package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.dto.ClienteDireccionDTO;
import com.example.demo.model.dto.DireccionDTO;

public interface DireccionService {

	public List<ClienteDireccionDTO> findAllByCliente(ClienteDTO clienteDTO);

    public DireccionDTO findById(Long id);

    public void save(DireccionDTO direccionDTO, Long idCliente);

    public void deleteById(Long id);
    
    public List<DireccionDTO> findAllDireccionesDisponibles();
    
    public void asignarDireccionACliente(Long idCliente, Long idDireccion);
}