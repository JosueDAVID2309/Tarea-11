package com.rosatel.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rosatel.Service.ProductoService;
import com.rosatel.Model.Producto;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    public List<Producto> productos() {
        return service.obtenerProductos();
    }
    
    @GetMapping("/{id}")
    public Optional<Producto> producto(@PathVariable Long id) {
        return service.obtenerProducto(id);
    }

    @PostMapping
    public void crearProducto(@RequestBody Producto pro) {
        service.crearProducto(pro);
    }

    @PutMapping("/{id}")
    public Producto editarProducto(@PathVariable Long id, @RequestBody Producto pro) {
        return service.editarProducto(id, pro);
    }
    

}
