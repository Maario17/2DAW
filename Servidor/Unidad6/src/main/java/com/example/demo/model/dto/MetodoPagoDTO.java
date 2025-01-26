package com.example.demo.model.dto;

import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteMetodoPago;
import com.example.demo.repository.entity.MetodoPago;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetodoPagoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String tipo;
    private String titular;

    @ToString.Exclude
    private Set<ClienteMetodoPagoDTO> clienteMetodoPago = new HashSet<>();

    public static MetodoPagoDTO convertToDTO(MetodoPago metodoPago, ClienteDTO clienteDTO) {
        return MetodoPagoDTO.builder()
                .id(metodoPago.getId())
                .tipo(metodoPago.getTipo())
                .titular(metodoPago.getTitular())
                .build();
    }

    public static MetodoPago convertToEntity(MetodoPagoDTO metodoPagoDTO, Cliente cliente) {
        MetodoPago metodoPago = MetodoPago.builder()
                .id(metodoPagoDTO.getId())
                .tipo(metodoPagoDTO.getTipo())
                .titular(metodoPagoDTO.getTitular())
                .build();
        
        ClienteMetodoPago cd = ClienteMetodoPago.builder()
                .cliente(cliente)
                .metodoPago(metodoPago)
                .build();

        return metodoPago;
    }
}
