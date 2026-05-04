package com.rosatel.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rosatel.Model.Categoria;
import com.rosatel.Model.Producto;
import com.rosatel.Repository.CategoriaRepository;
import com.rosatel.Repository.ProductoRepository;

import jakarta.annotation.Nullable;

@Service
public class ProductoService {
    private final ProductoRepository repoProducto;
    private final CategoriaRepository repoCategoria;

    public ProductoService(ProductoRepository repoProducto,
            CategoriaRepository repoCategoria) {
        this.repoProducto = repoProducto;
        this.repoCategoria = repoCategoria;
    }

    public List<Producto> listarProductos(@Nullable Long idCategoria) {

        if (idCategoria == null) {
            return repoProducto.findAll();
        }

        Categoria categoria = repoCategoria.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        List<Long> categoriasIds = new ArrayList<>();
        categoriasIds.add(idCategoria);

        if (categoria.getCategoria_padre() == null) {
            List<Categoria> hijas = repoCategoria.findByCategoria_padre(idCategoria);

            for (Categoria hija : hijas) {
                categoriasIds.add(hija.getId());
            }
        }

        return repoProducto.findByCategoriaIdIn(categoriasIds);
    }

    public Producto buscarProducto(Long id) {
        Producto producto = repoProducto.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return producto;
    }

    public void eliminarProducto(Long id) {
        if (!repoProducto.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }

        repoProducto.deleteById(id);
    }

    public void agregarProducto(Producto producto) {
        repoProducto.save(producto);
    }

    public void editarProducto(Producto producto) {
        repoProducto.save(producto);
    }
}
