package com.example.demo.model.dto;

import com.example.demo.repository.entity.Cuenta;
import com.example.demo.repository.entity.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoDTO {

    private Integer id;
    private String tipo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaOperacion;
    
    private String descripcion;
    private Float importe;
    private CuentaDTO cuentaOrigen;
    private CuentaDTO cuentaDestino;

    public static MovimientoDTO convertToDTO(Movimiento movimiento) {
        return MovimientoDTO.builder()
                .id(movimiento.getId())
                .tipo(movimiento.getTipo())
                .fechaOperacion(movimiento.getFechaOperacion())
                .descripcion(movimiento.getDescripcion())
                .importe(movimiento.getImporte())
                .cuentaOrigen(CuentaDTO.convertToDTO(movimiento.getCuentaOrigen()))
                .cuentaDestino(CuentaDTO.convertToDTO(movimiento.getCuentaDestino()))
                .build();
    }

    public static Movimiento convertToEntity(MovimientoDTO movimientoDTO, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        return Movimiento.builder()
                .id(movimientoDTO.getId())
                .tipo(movimientoDTO.getTipo())
                .fechaOperacion(movimientoDTO.getFechaOperacion())
                .descripcion(movimientoDTO.getDescripcion())
                .importe(movimientoDTO.getImporte())
                .cuentaOrigen(cuentaOrigen)
                .cuentaDestino(cuentaDestino)
                .build();
    }
}
