package com.example.demo.model.dto;

import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteDireccion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;
    private RecomendacionDTO recomendacionDTO;
    private Set<CuentaDTO> cuentas;
    private Set<DireccionDTO> direcciones;
    private Set<DepartamentoDTO> departamentos;
    private Set<MetodoPagoDTO> metodoPago;
    
    @DateTimeFormat(iso=ISO.DATE)
    private Date fechaNacimiento;

    public static ClienteDTO convertToDTO(Cliente c) {
        ClienteDTO cDTO = ClienteDTO.builder()
                .id(c.getId())
                .nif(c.getNif())
                .nombre(c.getNombre())
                .apellidos(c.getApellidos())
                .claveSeguridad(c.getClaveSeguridad())
                .email(c.getEmail())
                .recomendacionDTO(RecomendacionDTO.convertToDTO(c.getRecomendacion()))
                .cuentas(c.getCuentas() != null ? c.getCuentas().stream().map(CuentaDTO::convertToDTO).collect(Collectors.toSet()) : new HashSet<>())
                .direcciones(c.getListaClientesDirecciones() != null ? c.getListaClientesDirecciones().stream().map(cd -> DireccionDTO.convertToDTO(cd.getDireccion(), ClienteDTO.builder()
                        .id(c.getId())
                        .nif(c.getNif())
                        .nombre(c.getNombre())
                        .apellidos(c.getApellidos())
                        .build())).collect(Collectors.toSet()) : new HashSet<>())
                .fechaNacimiento(c.getFechaNacimiento())
                .build();
        return cDTO;
    }

    public static Cliente convertToEntity(ClienteDTO cDTO) {
        Cliente c = Cliente.builder()
                .id(cDTO.getId())
                .nif(cDTO.getNif())
                .nombre(cDTO.getNombre())
                .apellidos(cDTO.getApellidos())
                .claveSeguridad(cDTO.getClaveSeguridad())
                .email(cDTO.getEmail())
                .fechaNacimiento(cDTO.getFechaNacimiento())
                .build();
        c.setRecomendacion(RecomendacionDTO.convertToEntity(cDTO.getRecomendacionDTO(), c));
        c.setCuentas(cDTO.getCuentas() != null ? cDTO.getCuentas().stream().map(cuentaDTO -> CuentaDTO.convertToEntity(cuentaDTO, c)).collect(Collectors.toSet()) : new HashSet<>());
        c.setListaClientesDirecciones(cDTO.getDirecciones() != null ? cDTO.getDirecciones().stream().map(direccionDTO -> {
            ClienteDireccion clienteDireccion = ClienteDireccion.builder()
                    .cliente(c)
                    .direccion(DireccionDTO.convertToEntity(direccionDTO, c))
                    .build();
            return clienteDireccion;
        }).collect(Collectors.toList()) : new ArrayList<>());
        return c;
    }
}
