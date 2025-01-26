package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.repository.dao.ClienteRepository;
import com.example.demo.repository.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<ClienteDTO> findAll() {
        log.info("Obteniendo todos los clientes");
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findById(Long id) {
        log.info("Obteniendo cliente con ID: {}", id);
        return clienteRepository.findById(id)
                .map(ClienteDTO::convertToDTO)
                .orElse(null);
    }
    
    
    @Override
    public void save(ClienteDTO clienteDTO) {
        log.info("Guardando cliente: {}", clienteDTO);
        Cliente cliente = ClienteDTO.convertToEntity(clienteDTO);
        if (cliente.getId() != null) {
            Cliente clienteExistente = clienteRepository.findById(cliente.getId()).orElse(null);
            if (clienteExistente != null) {
                if (clienteDTO.getDirecciones() == null || clienteDTO.getDirecciones().isEmpty()) {
                    cliente.setListaClientesDirecciones(clienteExistente.getListaClientesDirecciones());
                }
            }
        }
        clienteRepository.save(cliente);
    }

    
    
    @Override
    public void deleteById(Long id) {
        log.info("Eliminando cliente con ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            log.warn("Cliente con ID: {} no encontrado", id);
            return;
        }
        clienteRepository.deleteById(id);
    }
}
