package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.repository.entity.ClienteDireccion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDireccionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private ClienteDTO clienteDTO;
    private DireccionDTO direccionDTO;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAlta;

    public static ClienteDireccionDTO convertToDTO(ClienteDireccion clienteDireccion) {
        return ClienteDireccionDTO.builder()
                .id(clienteDireccion.getId())
                .clienteDTO(ClienteDTO.convertToDTO(clienteDireccion.getCliente()))
                .direccionDTO(DireccionDTO.convertToDTO(clienteDireccion.getDireccion(), 
                        ClienteDTO.convertToDTO(clienteDireccion.getCliente())))
                .fechaAlta(clienteDireccion.getFechaAlta())
                .build();
    }
    
}