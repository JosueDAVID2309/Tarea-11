package com.rosatel.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rosatel.DTO.ApiResponse;
import com.rosatel.Model.Categoria;
import com.rosatel.Service.CategoriaService;

import jakarta.annotation.Nullable;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ApiResponse<List<Categoria>> listarCategorias(@Nullable @PathVariable Long id) {
        try{
            List<Categoria> categorias = service.listarCategorias(id);
            return new ApiResponse<List<Categoria>>(true, categorias, "Categorias Encontradas");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

    @PostMapping
    public ApiResponse<Categoria> agregarCategoria(@RequestBody Categoria categoria) {
        try{
            Categoria newCategoria = service.agregarCategoria(categoria);
            return new ApiResponse<Categoria>(true, newCategoria, "Categoria Creada");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Categoria> editarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try{
            categoria.setId(id);
            Categoria newCategoria = service.editarCategoria(categoria);
            return new ApiResponse<Categoria>(true, newCategoria, "Categoria Editada");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

    @DeleteMapping("/{id}") 
    public ApiResponse<String> eliminarCategoria(@PathVariable Long id){
        try{
            service.eliminarCategoria(id);
            return new ApiResponse<String>(true, null, "Categoria eliminada");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }
    
    

}
