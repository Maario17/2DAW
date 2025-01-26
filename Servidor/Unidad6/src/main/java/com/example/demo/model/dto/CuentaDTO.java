package com.example.demo.model.dto;

import com.example.demo.repository.entity.Cuenta;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaDTO {

    private Long id;
    private String banco;
    private String sucursal;
    private String dc;
    private String numeroCuenta;
    private float saldoActual;
    
    // Lista de movimientos asociados a la cuenta
    private List<MovimientoDTO> movimientos;  // Asegúrate de crear un DTO para Movimiento (MovimientoDTO)

    public static CuentaDTO convertToDTO(Cuenta c) {
        return CuentaDTO.builder()
                .id(c.getId())
                .banco(c.getBanco())
                .sucursal(c.getSucursal())
                .dc(c.getDc())
                .numeroCuenta(c.getNumeroCuenta())
                .saldoActual(c.getSaldoActual())
                .build();
    }

    public static Cuenta convertToEntity(CuentaDTO cDTO, Cliente cliente) {
        return Cuenta.builder()
                .id(cDTO.getId())
                .banco(cDTO.getBanco())
                .sucursal(cDTO.getSucursal())
                .dc(cDTO.getDc())
                .numeroCuenta(cDTO.getNumeroCuenta())
                .saldoActual(cDTO.getSaldoActual())
                .cliente(cliente)
                .build();
    }
}
