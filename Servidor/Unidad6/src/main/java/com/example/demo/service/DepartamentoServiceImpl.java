package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.DepartamentoDTO;
import com.example.demo.model.dto.ClienteDepartamentoDTO;
import com.example.demo.repository.dao.ClienteDepartamentoRepository;
import com.example.demo.repository.dao.ClienteRepository;
import com.example.demo.repository.dao.DepartamentoRepository;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteDepartamento;
import com.example.demo.repository.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private static final Logger log = LoggerFactory.getLogger(DepartamentoServiceImpl.class);

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteDepartamentoRepository clienteDepartamentoRepository;

    @Override
    public List<ClienteDepartamentoDTO> findAllByCliente(Long idCliente) {
        log.info("DepartamentoServiceImpl - findAllByCliente: Lista de todos los departamentos del cliente con ID: " + idCliente);

        List<ClienteDepartamento> lista = clienteDepartamentoRepository.findByClienteId(idCliente);
        List<ClienteDepartamentoDTO> listaClientesDepartamentosDTO = new ArrayList<>();
        for (ClienteDepartamento clienteDepartamento : lista) {
            listaClientesDepartamentosDTO.add(ClienteDepartamentoDTO.convertToDTO(clienteDepartamento));
        }

        return listaClientesDepartamentosDTO;
    }

    @Override
    public DepartamentoDTO findById(Long id) {
        log.info("DepartamentoServiceImpl - findById: Buscando departamento con ID: " + id);

        Optional<Departamento> departamento = departamentoRepository.findById(id);
        return departamento.map(d -> DepartamentoDTO.convertToDTO(d, null)).orElse(null);
    }
    

    @Override
    public void save(DepartamentoDTO departamentoDTO, Long idCliente) {
        log.info(this.getClass().getSimpleName() + " save: guardar departamento con datos: {}", departamentoDTO);

        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        
        Departamento departamento = DepartamentoDTO.convertToEntity(departamentoDTO, cliente);
        this.departamentoRepository.save(departamento);

        if (departamentoDTO.getId() == null) {
            if (cliente != null) {
                cliente.getListaClientesDepartamentos().add(ClienteDepartamento.builder()
                        .cliente(cliente)
                        .departamento(departamento)
                        .fechaAlta(new Date())
                        .build());
                clienteRepository.save(cliente);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("DepartamentoServiceImpl - deleteById: Eliminando departamento con ID: " + id);

        departamentoRepository.deleteById(id);
    }

}
