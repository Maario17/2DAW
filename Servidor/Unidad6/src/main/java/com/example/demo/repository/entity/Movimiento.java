package com.example.demo.repository.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "movimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "fechaoperacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacion;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "importe", nullable = false)
    private Float importe;

    @ManyToOne()
    @JoinColumn(name = "idcuentaorigen", nullable = false)
    private Cuenta cuentaOrigen;

    @ManyToOne()
    @JoinColumn(name = "idcuentadestino", nullable = false)
    private Cuenta cuentaDestino;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
    
}
