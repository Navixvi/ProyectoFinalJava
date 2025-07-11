package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listarTodos();

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(int id);

    void eliminar(int id);
}
