package com.inditex.hiring.service;

import com.inditex.hiring.model.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> getProductos();

    Producto getProductoById(Long id);

    Producto saveProducto(Producto producto);

    void deleteProducto(Long id);

    void deleteAllProductos();

    List<Producto> searchByPartNum(Integer brandId, String partnumber);

}
