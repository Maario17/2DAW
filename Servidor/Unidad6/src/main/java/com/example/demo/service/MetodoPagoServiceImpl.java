package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ClienteMetodoPagoDTO;
import com.example.demo.model.dto.MetodoPagoDTO;
import com.example.demo.repository.dao.ClienteMetodoPagoRepository;
import com.example.demo.repository.dao.ClienteRepository;
import com.example.demo.repository.dao.MetodoPagoRepository;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteDepartamento;
import com.example.demo.repository.entity.ClienteMetodoPago;
import com.example.demo.repository.entity.MetodoPago;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private static final Logger log = LoggerFactory.getLogger(MetodoPagoServiceImpl.class);

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMetodoPagoRepository clienteMetodoPagoRepository;

    @Override
    public List<ClienteMetodoPagoDTO> findAllByCliente(Long idCliente) {
        log.info("MetodoPagoServiceImpl - findAllByCliente: Lista de todos los métodos de pago del cliente con ID: " + idCliente);

        List<ClienteMetodoPago> lista = clienteMetodoPagoRepository.findByClienteId(idCliente);
        List<ClienteMetodoPagoDTO> listaClientesMetodoPagoDTO = new ArrayList<>();
        
        for (ClienteMetodoPago clienteMetodoPago : lista) {
            ClienteMetodoPagoDTO clienteMetodoPagoDTO = ClienteMetodoPagoDTO.convertToDTO(clienteMetodoPago);
            listaClientesMetodoPagoDTO.add(clienteMetodoPagoDTO);
        }

        return listaClientesMetodoPagoDTO;
    }

    @Override
    public MetodoPagoDTO findById(Long id) {
        log.info("MetodoPagoServiceImpl - findById: Buscando método de pago con ID: " + id);

        Optional<MetodoPago> metodoPago = metodoPagoRepository.findById(id);
        return metodoPago.map(m -> MetodoPagoDTO.convertToDTO(m, null)).orElse(null);
    }

    @Override
    public void save(MetodoPagoDTO metodoPagoDTO, Long idCliente) {
        log.info("MetodoPagoServiceImpl - save: Guardando método de pago con datos: {}", metodoPagoDTO);
        
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        
        MetodoPago metodoPago = MetodoPagoDTO.convertToEntity(metodoPagoDTO, cliente);
        metodoPagoRepository.save(metodoPago);
        
        if (metodoPagoDTO.getId() == null) {
            if (cliente != null) {
                cliente.getListaClienteMetodoPago().add(ClienteMetodoPago.builder()
                        .cliente(cliente)
                        .metodoPago(metodoPago)
                        .fechaAlta(new Date())
                        .build());
                clienteRepository.save(cliente);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("MetodoPagoServiceImpl - deleteById: Eliminando método de pago con ID: " + id);

        metodoPagoRepository.deleteById(id);
    }
}
