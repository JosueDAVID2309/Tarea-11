package com.rosatel.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Resena")
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @Column(name = "created_at", updatable = false)
    private String createdAt;

}
