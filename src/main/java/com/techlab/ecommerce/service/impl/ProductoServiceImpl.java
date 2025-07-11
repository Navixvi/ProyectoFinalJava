package com.techlab.ecommerce.service.impl;

import com.techlab.ecommerce.entity.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;
import com.techlab.ecommerce.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<Producto> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public Optional<Producto> buscarPorId(int id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }
}
