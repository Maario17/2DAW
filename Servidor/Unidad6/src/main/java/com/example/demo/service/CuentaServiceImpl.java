package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CuentaDTO;
import com.example.demo.repository.dao.ClienteRepository;
import com.example.demo.repository.dao.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

    private static final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<CuentaDTO> findAllByCliente(Long idCliente) {
        log.info("Obteniendo todas las cuentas del cliente con ID: {}", idCliente);
        return cuentaRepository.findAllByCliente(idCliente)
                .stream()
                .map(CuentaDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO findById(Long id) {
        log.info("Obteniendo cuenta con ID: {}", id);
        return cuentaRepository.findById(id)
                .map(CuentaDTO::convertToDTO)
                .orElse(null);
    }

    @Override
    public void save(CuentaDTO cuentaDTO, Long idCliente) {
        log.info("Guardando cuenta: {}", cuentaDTO);
        var cliente = clienteRepository.findById(idCliente).orElseThrow(() -> {
            log.error("Cliente con ID: {} no encontrado", idCliente);
            return new IllegalArgumentException("Cliente no encontrado");
        });
        cuentaRepository.save(CuentaDTO.convertToEntity(cuentaDTO, cliente));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminando cuenta con ID: {}", id);
        if (!cuentaRepository.existsById(id)) {
            log.warn("Cuenta con ID: {} no encontrada", id);
            return;
        }
        cuentaRepository.deleteById(id);
    }
    
    @Override
    public List<CuentaDTO> findAll() {
        return cuentaRepository.findAll()
                .stream()
                .map(CuentaDTO::convertToDTO)
                .collect(Collectors.toList());
    }
}
