package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión de productos e inventario.
 */
public interface IProductoService {

    @Transactional(readOnly = true)
    List<Producto> listarTodos();

    /**
     * Busca un producto por su identificador.
     * @param id El ID del producto.
     * @return Optional del producto.
     */
    Optional<Producto> buscarPorId(Long id);

    /**
     * Guarda o actualiza un producto (incluyendo la actualización de stock).
     * @param producto El producto a guardar.
     * @return El producto guardado.
     */
    Producto guardar(Producto producto);

    @Transactional(readOnly = true)
    List<Producto> buscarPorMarca(String marcaNombre);

    @Transactional
    void eliminar(Long id);

    // Otros métodos (listar, eliminar) se pueden añadir aquí si son necesarios
}