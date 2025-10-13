package com.example.api_productos.controller;
import org.springframework.http.ResponseEntity;


import com.example.api_productos.entity.Producto;
import com.example.api_productos.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> listar() {
        return repository.findAll();
    }

   @GetMapping("/{id}")
public ResponseEntity<Producto> obtener(@PathVariable Long id) {
    return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
         /*{
        "nombre": "Laptop",
        "precio": 1299.99
    }
    */
        return repository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
         /*
    {
        "nombre": "Laptop actualizada",
        "precio": 1199.99
    }
    */
        Producto p = repository.findById(id).orElseThrow();
        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());
        return repository.save(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}