package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ProductoRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository; // Necesario para la búsqueda por marca

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, MarcaRepository marcaRepository) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    @Override
    public Producto guardar(Producto producto) {
        // Lógica de negocio: Ej. Asegurar que el precio no sea negativo
        if (producto.getPreciounitario() < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo.");
        }
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Producto> buscarPorMarca(String marcaNombre) {
        // Esto asume que tienes un método findByNombre en MarcaRepository
        // Por simplicidad, lo implementamos filtrando todos, pero en un entorno real
        // usarías consultas JPA.
        return productoRepository.findAll().stream()
                .filter(p -> p.getMarca() != null && p.getMarca().getNombre().equalsIgnoreCase(marcaNombre))
                .collect(Collectors.toList());
    }

    /**
     * Elimina un producto por su ID.
     * @param id El ID del producto a eliminar.
     */
    @Transactional
    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}