package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CuentaDTO;
import com.example.demo.model.dto.MovimientoDTO;
import com.example.demo.repository.dao.MovimientoRepository;
import com.example.demo.repository.entity.Cuenta;
import com.example.demo.repository.entity.Movimiento;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public List<MovimientoDTO> findAll(CuentaDTO cuentaDTO) {
        log.info("Obteniendo todos los movimientos para la cuenta ID: {}", cuentaDTO.getId());
        return movimientoRepository.findAllByCuentaOrigenId(cuentaDTO.getId())
                .stream()
                .map(MovimientoDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDTO findById(Long id) {
        log.info("Obteniendo movimiento con ID: {}", id);
        return movimientoRepository.findById(id)
                .map(MovimientoDTO::convertToDTO)
                .orElse(null);
    }

    @Override
    public void save(MovimientoDTO movimientoDTO) {
        log.info("Guardando movimiento: {}", movimientoDTO);
        Cuenta cuentaOrigen = Cuenta.builder().id(movimientoDTO.getCuentaOrigen().getId()).build();
        Cuenta cuentaDestino = Cuenta.builder().id(movimientoDTO.getCuentaDestino().getId()).build();
        Movimiento movimiento = MovimientoDTO.convertToEntity(movimientoDTO, cuentaOrigen, cuentaDestino);
        movimientoRepository.save(movimiento);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminando movimiento con ID: {}", id);
        if (!movimientoRepository.existsById(id)) {
            log.warn("Movimiento con ID: {} no encontrado", id);
            return;
        }
        movimientoRepository.deleteById(id);
    }
}
