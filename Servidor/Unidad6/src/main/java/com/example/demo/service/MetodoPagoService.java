package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClienteMetodoPagoDTO;
import com.example.demo.model.dto.MetodoPagoDTO;

public interface MetodoPagoService {

    List<ClienteMetodoPagoDTO> findAllByCliente(Long idCliente);

    MetodoPagoDTO findById(Long id);

    void save(MetodoPagoDTO metodoPagoDTO, Long id);

    void deleteById(Long id);
}
