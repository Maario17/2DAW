package com.example.demo.model.dto;

import com.example.demo.repository.entity.Departamento;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.ClienteDepartamento;

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
public class DepartamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String ciudad;
    private String especializacion;

    @ToString.Exclude
    private List<ClienteDTO> listaClientesDTO = new ArrayList<>();

    public static DepartamentoDTO convertToDTO(Departamento departamento, ClienteDTO clienteDTO) {
        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .id(departamento.getId())
                .ciudad(departamento.getCiudad())
                .especializacion(departamento.getEspecializacion())
                .build();

        return departamentoDTO;
    }

    public static Departamento convertToEntity(DepartamentoDTO departamentoDTO, Cliente cliente) {
        Departamento departamento = Departamento.builder()
                .id(departamentoDTO.getId())
                .ciudad(departamentoDTO.getCiudad())
                .especializacion(departamentoDTO.getEspecializacion())
                .build();

        ClienteDepartamento cd = ClienteDepartamento.builder()
                .cliente(cliente)
                .departamento(departamento)
                .build();

        return departamento;
    }
}
