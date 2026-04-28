package com.rosatel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rosatel.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
