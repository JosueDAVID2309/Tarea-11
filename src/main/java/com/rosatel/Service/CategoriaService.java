package com.rosatel.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rosatel.Model.Categoria;
import com.rosatel.Repository.CategoriaRepository;

import jakarta.annotation.Nullable;

@Service
public class CategoriaService {

    private final CategoriaRepository repoCategoria;

    public CategoriaService(CategoriaRepository repoCategoria) {
        this.repoCategoria = repoCategoria;
    }

    public List<Categoria> listarCategorias(@Nullable Long idCategoria) {
        if (idCategoria == null) {
            return repoCategoria.findAll();
        }
        Categoria categoria = repoCategoria.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("No hay existe alguna categoria con este id: " + idCategoria));
        return repoCategoria.findByCategoria_padre(categoria.getId());

    }

    public Categoria agregarCategoria(Categoria categoria) {
        controlErrores(categoria);
        return repoCategoria.save(categoria);
    }

    public Categoria editarCategoria(Categoria categoria) {
        return repoCategoria.save(categoria);
    }

    public void eliminarCategoria(Long idCategoria){
        if(repoCategoria.existsById(idCategoria)){
            repoCategoria.deleteById(idCategoria);
        }
        throw new RuntimeException("No existe una categoria con id: "+idCategoria);
        
    }

    public void controlErrores(Categoria categoria){
        if (repoCategoria.findByNombre(categoria.getNombre()) != null) {
            throw new RuntimeException("Ya existe una categoria con este nombre");
        }

        if (categoria.getCategoria_padre() != null) {
            if (!repoCategoria.existsById(categoria.getCategoria_padre().getId())) {
                throw new RuntimeException("No existe la categoria Padre");
            }
        }
    }
}
