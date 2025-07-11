package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.entity.Producto;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody @Valid Producto producto) {
        Producto creado = service.guardar(producto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> actualizarParcialProducto(@PathVariable int id, @RequestBody Producto producto) {
        Producto existente = service.buscarPorId(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado."));

        if (producto.getNombre() != null) {
            existente.setNombre(producto.getNombre());
        }
        if (producto.getDescripcion() != null) {
            existente.setDescripcion(producto.getDescripcion());
        }
        if (producto.getPrecio() != 0) {
            existente.setPrecio(producto.getPrecio());
        }
        if (producto.getCategoria() != null) {
            existente.setCategoria(producto.getCategoria());
        }
        if (producto.getImagenUrl() != null) {
            existente.setImagenUrl(producto.getImagenUrl());
        }
        if (producto.getStock() != 0) {
            existente.setStock(producto.getStock());
        }

        Producto actualizado = service.guardar(existente);
        return ResponseEntity.ok(actualizado);
    }

}
