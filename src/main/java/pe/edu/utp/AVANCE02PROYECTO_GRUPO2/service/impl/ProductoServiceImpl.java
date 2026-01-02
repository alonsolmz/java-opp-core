package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception.ResourceNotFoundException;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.MarcaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ProductoRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProductoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, MarcaRepository marcaRepository) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository; // <--- Inicializar la nueva dependencia
    }

    @Override
    public List<Producto> buscarPorMarcaNombre(String marcaNombre) { // <--- Nombre de método corregido




        return productoRepository.findAll().stream()
                .filter(p -> p.getMarca() != null && p.getMarca().getNombre().equalsIgnoreCase(marcaNombre))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Producto guardar(Producto producto) {

        if (producto.getPreciounitario() == null || producto.getPreciounitario().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser nulo o negativo.");
        }


        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser nulo o negativo.");
        }


        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }



    @Override
    public Optional<Producto> buscarPorId(Long id) { // <--- Cambiar el tipo de retorno aquí
        return productoRepository.findById(id); // <--- Ahora es correcto
    }


    @Override
    public List<Producto> buscarPorMarca(String marcaNombre) {

        return productoRepository.findAll().stream()
                .filter(p -> p.getMarca() != null && p.getMarca().getNombre().equalsIgnoreCase(marcaNombre))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void eliminar(Long id) {


        productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));


        productoRepository.deleteById(id);
    }
}