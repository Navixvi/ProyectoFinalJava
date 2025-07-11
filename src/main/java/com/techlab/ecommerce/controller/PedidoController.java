package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.entity.LineaPedido;
import com.techlab.ecommerce.entity.Pedido;
import com.techlab.ecommerce.entity.Producto;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody @Valid Pedido pedido){
        double total = 0;

        for (LineaPedido linea : pedido.getLineas()) {
            Producto p = productoRepo.findById(linea.getProductoId())
                    .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + linea.getProductoId() + " no encontrado."));


            if (linea.getCantidad() > p.getStock()) {
                throw new StockInsuficienteException("Stock insuficiente para: " + p.getNombre());
            }

            p.setStock(p.getStock() - linea.getCantidad());
            total += p.getPrecio() * linea.getCantidad();
            productoRepo.save(p);
        }

        pedido.setTotal(total);
        pedidoRepo.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
