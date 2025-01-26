package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.repository.entity.ClienteDepartamento;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDepartamentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private ClienteDTO clienteDTO;
    private DepartamentoDTO departamentoDTO;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAlta;

    public static ClienteDepartamentoDTO convertToDTO(ClienteDepartamento clienteDepartamento) {
        return ClienteDepartamentoDTO.builder()
                .id(clienteDepartamento.getId())
                .clienteDTO(ClienteDTO.convertToDTO(clienteDepartamento.getCliente()))
                .departamentoDTO(DepartamentoDTO.convertToDTO(clienteDepartamento.getDepartamento(), 
                        ClienteDTO.convertToDTO(clienteDepartamento.getCliente())))
                .fechaAlta(clienteDepartamento.getFechaAlta())
                .build();
    }
}
