package com.rosatel.Controller;
import org.springframework.web.bind.annotation.*;
import com.rosatel.DTO.ApiResponse;
import com.rosatel.Model.Producto;
import com.rosatel.Service.ProductoService;
import jakarta.annotation.Nullable;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    
    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Producto>> listarProductos(@Nullable @RequestBody Long idCategoria){
        try{
            List<Producto> productos = service.listarProductos(idCategoria);
            return new ApiResponse<List<Producto>>(true, productos, !productos.isEmpty() ?"Productos Encontrados" : "Parece que no hay productos");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<Producto> buscarProducto(@PathVariable Long id) {
        try{
            Producto producto = service.buscarProducto(id);
            return new ApiResponse<Producto>(true, producto, producto != null ? "Producto encontrado" : "Producto Inexistente");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
        
    }
    
    @PostMapping
    public ApiResponse<Producto> agregarProducto(@RequestBody Producto producto) {
        try{
            Producto newProducto = service.agregarProducto(producto);
            return new ApiResponse<Producto>(true, newProducto, "Producto creado");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Producto> editarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try{
            producto.setId(id);
            Producto editProducto = service.editarProducto(producto);
            return new ApiResponse<Producto>(true, editProducto, "Producto editado");
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> eliminarProducto(@PathVariable Long id){
        try{
            service.eliminarProducto(id);
            return new ApiResponse<String>(true, null, "Se elimino el registro con id: "+id);
        }catch(RuntimeException e){
            return new ApiResponse<>(false, null, e.toString());
        }
    }

}
