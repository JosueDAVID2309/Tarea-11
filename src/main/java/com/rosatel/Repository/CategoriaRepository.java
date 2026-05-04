package com.rosatel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosatel.Model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNombre(String nombre);
    List<Categoria> findByCategoria_padre(Long idCategoria);
}
