package com.example.demo.model.dto;

import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.Recomendacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecomendacionDTO {

    private Long id;
    private String observaciones;

    public static RecomendacionDTO convertToDTO(Recomendacion r) {
        return RecomendacionDTO.builder()
                .id(r.getId())
                .observaciones(r.getObservaciones())
                .build();
    }

    public static Recomendacion convertToEntity(RecomendacionDTO rDTO, Cliente cliente) {
        return Recomendacion.builder()
                .id(rDTO.getId())
                .observaciones(rDTO.getObservaciones())
                .cliente(cliente)
                .build();
    }
}
