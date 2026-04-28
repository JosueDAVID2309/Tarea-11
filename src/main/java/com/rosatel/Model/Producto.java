package com.rosatel.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // SKU Único
    @Column(name = "sku", unique = true, nullable = false)
    private String sku;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio")
    private double precio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private Categoria categoria;

    @Column(name = "esFavorito")
    private boolean esFavorito = false;

    @Column(name = "created_at", updatable = false)
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;
}
