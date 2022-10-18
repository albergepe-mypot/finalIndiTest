package com.inditex.hiring.service;

import com.inditex.hiring.model.Producto;
import com.inditex.hiring.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Producto> getProductos() {

        return (List<Producto>)productoRepository.findAll();
    }

    public Producto getProductoById(Long id)
    {
        return productoRepository.findOne(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id)
    {
         productoRepository.delete(id);
    }

    public void deleteAllProductos()
    {
        productoRepository.deleteAll();
    }

    public List<Producto> searchByPartNum(Integer brandId, String partnumber)
    {
         return productoRepository.searchByBrandAndPart(brandId, partnumber);
    }






}
