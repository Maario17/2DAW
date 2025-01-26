package com.example.demo.model.dto;

import com.example.demo.repository.entity.Direccion;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteDireccion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String descripcion;
    private String pais;
    private String cp;

    @ToString.Exclude
    private List<ClienteDTO> listaClientesDTO = new ArrayList<>();

    public static DireccionDTO convertToDTO(Direccion direccion, ClienteDTO clienteDTO) {
        DireccionDTO direccionDTO = DireccionDTO.builder()
                .id(direccion.getId())
                .descripcion(direccion.getDescripcion())
                .pais(direccion.getPais())
                .cp(direccion.getCp())
                .build();
        
        if (direccionDTO.getListaClientesDTO() == null) {
            direccionDTO.setListaClientesDTO(new ArrayList<>());
        }
        
        direccionDTO.getListaClientesDTO().add(clienteDTO);
        
        return direccionDTO;
    }

    public static Direccion convertToEntity(DireccionDTO direccionDTO, Cliente cliente) {
        Direccion direccion = Direccion.builder()
                .id(direccionDTO.getId())
                .descripcion(direccionDTO.getDescripcion())
                .pais(direccionDTO.getPais())
                .cp(direccionDTO.getCp())
                .build();

        ClienteDireccion cd = ClienteDireccion.builder()
                .cliente(cliente)
                .direccion(direccion)
                .build();
        
        if (direccion.getListaClientesDirecciones() == null) {
            direccion.setListaClientesDirecciones(new HashSet<>());
        }

        direccion.getListaClientesDirecciones().add(cd);
      
        cliente.getListaClientesDirecciones().add(cd);

        return direccion;
    }
}