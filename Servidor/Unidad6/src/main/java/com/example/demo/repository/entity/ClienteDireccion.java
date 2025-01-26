package com.example.demo.repository.entity;

import java.util.Date;
import java.util.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientesdirecciones")
public class ClienteDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "iddireccion")
    private Direccion direccion;
    
    @Column(name = "fechaalta")
    private Date fechaAlta;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ClienteDireccion other = (ClienteDireccion) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}