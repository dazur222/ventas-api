package com.elgardenia.ventas_api.producto;

import org.springframework.stereotype.Component;

import com.elgardenia.ventas_api.producto.dto.ProductoRequest;
import com.elgardenia.ventas_api.producto.dto.ProductoResponse;

@Component 
public class ProductoMapper {
    public ProductoResponse entityToDto(Producto producto){
        return  new ProductoResponse(producto.getId(),producto.getCerveza(),producto.getPresentacion(),producto.getPrecioBase(),producto.getActivo());
   }

   public Producto dtoToEntity(ProductoRequest request){
    Producto producto = new Producto();
    producto.setPrecioBase(request.precioBase());

    return producto;
   }
    
    
}
