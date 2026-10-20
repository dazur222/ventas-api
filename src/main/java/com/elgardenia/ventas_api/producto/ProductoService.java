package com.elgardenia.ventas_api.producto;

import com.elgardenia.ventas_api.cerveza.CervezaRepository;
import com.elgardenia.ventas_api.cerveza.CervezaService;
import com.elgardenia.ventas_api.presentacion.PresentacionRepository;
import com.elgardenia.ventas_api.presentacion.PresentacionService;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elgardenia.ventas_api.cerveza.Cerveza;
import com.elgardenia.ventas_api.presentacion.Presentacion;
import com.elgardenia.ventas_api.producto.dto.ProductoRequest;
import com.elgardenia.ventas_api.producto.dto.ProductoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final PresentacionRepository presentacionRepository;
    private final PresentacionService presentacionService;
    private final CervezaService cervezaService;
    private final CervezaRepository cervezaRepository;
    private final ProductoMapper mapper;
    private final ProductoRepository repository;



    public List<ProductoResponse> getProductos() {
        return repository.findAll().stream().map(mapper::entityToDto).toList();
    }

    public ProductoResponse postProducto(ProductoRequest request) {

        Producto producto = mapper.dtoToEntity(request);
        Cerveza cerveza = new Cerveza();
        Presentacion presentacion = new Presentacion();

        cerveza = cervezaService.getCerveza(request.cervezaId());
        presentacion = presentacionService.getPresentacion(request.presentacionId());
        producto.setCerveza(cerveza);
        producto.setPresentacion(presentacion);
        producto.setActivo(true);

        Producto nuevoProducto = repository.save(producto);
        return mapper.entityToDto(producto);

    }


    public ProductoResponse updateProducto(ProductoRequest request,Integer id){
        Producto producto = repository.findById(id).orElse(null);
        Cerveza cerveza = cervezaRepository.findById(request.cervezaId()).orElse(null);
        Presentacion presentacion = presentacionRepository.findById(request.presentacionId()).orElse(null);

        producto.setCerveza(cerveza);
        producto.setPresentacion(presentacion);
        producto.setPrecioBase(request.precioBase());

        producto.setActivo(request.activo());
        
        System.out.println("");
        System.out.println("vamos a actualizar el producto");
        return mapper.entityToDto(repository.save(producto));
    


         
    }

}
