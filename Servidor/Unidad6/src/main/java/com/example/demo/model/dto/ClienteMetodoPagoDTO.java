package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.repository.entity.ClienteMetodoPago;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteMetodoPagoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAlta;

    private Boolean predeterminado;

    private ClienteDTO clienteDTO;
    private MetodoPagoDTO metodoPagoDTO;

    public static ClienteMetodoPagoDTO convertToDTO(ClienteMetodoPago clienteMetodoPago) {
        return ClienteMetodoPagoDTO.builder()
                .id(clienteMetodoPago.getId())
                .fechaAlta(clienteMetodoPago.getFechaAlta())
                .predeterminado(clienteMetodoPago.getPredeterminado())
                .clienteDTO(ClienteDTO.convertToDTO(clienteMetodoPago.getCliente()))
                .metodoPagoDTO(MetodoPagoDTO.convertToDTO(clienteMetodoPago.getMetodoPago(), 
                        ClienteDTO.convertToDTO(clienteMetodoPago.getCliente())))
                .build();
    }
}
