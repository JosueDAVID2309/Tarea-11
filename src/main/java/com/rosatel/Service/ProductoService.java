package com.rosatel.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rosatel.Model.Producto;
import com.rosatel.Repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo){
        this.repo = repo;
    }

    public List<Producto> obtenerProductos(){
        return repo.findAll();
    }

    public Optional<Producto> obtenerProducto(Long id) {
        return repo.findById(id);
    }

    public void crearProducto(Producto pro){
        repo.save(pro);
    }

    public Producto editarProducto(Long id, Producto pro){
        Producto producto = repo.findById(id).orElseThrow(() -> new RuntimeException("No existe el Producto"));

        producto.setNombre(pro.getNombre());
        producto.setSku(pro.getSku());
        producto.setStock(pro.getStock());
        producto.setPrecio(pro.getPrecio());
        producto.setDescripcion(pro.getDescripcion());
        producto.setCategoria(pro.getCategoria());
        // producto.setEsFavorito(pro.getEsFavorito());

        return repo.save(producto);
    }
    
}
