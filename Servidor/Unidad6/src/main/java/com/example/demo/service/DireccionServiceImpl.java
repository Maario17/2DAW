package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.dto.ClienteDireccionDTO;
import com.example.demo.model.dto.DireccionDTO;
import com.example.demo.repository.dao.ClienteDireccionRepository;
import com.example.demo.repository.dao.ClienteRepository;
import com.example.demo.repository.dao.DireccionRepository;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteDireccion;
import com.example.demo.repository.entity.Direccion;

@Service
public class DireccionServiceImpl implements DireccionService {

    private static final Logger log = LoggerFactory.getLogger(DireccionServiceImpl.class);

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteDireccionRepository clienteDireccionRepository;

    @Override
    public List<ClienteDireccionDTO> findAllByCliente(ClienteDTO clienteDTO) {
        log.info("DireccionServiceImpl - findAllByCliente: Lista de todas las direcciones del cliente: " 
                 + clienteDTO.getId());
        
        List<ClienteDireccion> lista = clienteDireccionRepository.findByClienteId(clienteDTO.getId());
        List<ClienteDireccionDTO> listaClientesDireccionesDTO = new ArrayList<>();
        for (ClienteDireccion clienteDireccion : lista) {
            listaClientesDireccionesDTO.add(ClienteDireccionDTO.convertToDTO(clienteDireccion));
        }
        
        return listaClientesDireccionesDTO;
    }

    @Override
    public DireccionDTO findById(Long id) {
        log.info("DireccionServiceImpl - findById: Buscando direccion con ID: " + id);
        
        Optional<Direccion> direccion = direccionRepository.findById(id);
        return direccion.map(d -> DireccionDTO.convertToDTO(d, null)).orElse(null);
    }

    @Override
    public void save(DireccionDTO direccionDTO, Long idCliente) {
        log.info("DireccionServiceImpl - save: Salvando la direccion del cliente con ID: " + idCliente);

        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if (cliente.isPresent()) {
            Direccion direccion = DireccionDTO.convertToEntity(direccionDTO, cliente.get());
            direccionRepository.save(direccion);

            Optional<ClienteDireccion> cdo = clienteDireccionRepository
                    .findByClienteIdAndDireccionId(cliente.get().getId(), direccion.getId());

            ClienteDireccion cd = null;
            if (cdo.isPresent()) {
                cd = cdo.get();
                cd.setFechaAlta(new Date());
            } else {
                cd = new ClienteDireccion();
                cd.setCliente(cliente.get());
                cd.setDireccion(direccion);
                cd.setFechaAlta(new Date());
            }

            clienteDireccionRepository.save(cd);
            
        } else {
            log.warn("Cliente con ID: " + idCliente + " no encontrado.");
        }
    }


    @Override
    public void deleteById(Long id) {
        log.info("DireccionServiceImpl - deleteById: Eliminando direccion con ID: " + id);
        
        direccionRepository.deleteById(id);
    }
    
    @Override
    public List<DireccionDTO> findAllDireccionesDisponibles() {
        log.info("DireccionServiceImpl - findAllDireccionesDisponibles: Obteniendo todas las direcciones disponibles");
        List<Direccion> direcciones = direccionRepository.findAll();
        List<DireccionDTO> direccionesDTO = new ArrayList<>();
        for (Direccion direccion : direcciones) {
            direccionesDTO.add(DireccionDTO.convertToDTO(direccion, null));
        }

        return direccionesDTO;
    }
    
    @Override
    public void asignarDireccionACliente(Long idCliente, Long idDireccion) {
        log.info("DireccionServiceImpl - asignarDireccionACliente: Asignando direccion con ID: " 
                 + idDireccion + " al cliente con ID: " + idCliente);
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        Optional<Direccion> direccionOpt = direccionRepository.findById(idDireccion);

        if (clienteOpt.isPresent() && direccionOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            Direccion direccion = direccionOpt.get();
            Optional<ClienteDireccion> clienteDireccionOpt = clienteDireccionRepository
                    .findByClienteIdAndDireccionId(idCliente, idDireccion);

            if (!clienteDireccionOpt.isPresent()) {
                ClienteDireccion clienteDireccion = new ClienteDireccion();
                clienteDireccion.setCliente(cliente);
                clienteDireccion.setDireccion(direccion);
                clienteDireccion.setFechaAlta(new Date());

                clienteDireccionRepository.save(clienteDireccion);
                log.info("Direccion asignada correctamente al cliente");
            } else {
                log.warn("La direccion ya esta asignada al cliente");
            }
        } else {
            log.warn("No se ha encontrado la id de Cliente/Direccion");
        }
    }

}
