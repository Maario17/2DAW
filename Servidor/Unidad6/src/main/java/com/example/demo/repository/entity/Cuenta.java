package com.example.demo.repository.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String banco;

    private String sucursal;

    private String dc;

    @Column(name = "numerocuenta")
    private String numeroCuenta;

    @Column(name = "saldoactual")
    private float saldoActual;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL)
    private List<Movimiento> movimientosOrigen;

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL)
    private List<Movimiento> movimientosDestino;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cuenta other = (Cuenta) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
