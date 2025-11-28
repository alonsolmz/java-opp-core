package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para la gestión de Ventas.
 */
public interface IVentaService {

    /**
     * Registra una nueva venta, incluyendo la validación de stock y la actualización del inventario.
     * @param venta El objeto Venta a registrar.
     * @return La venta registrada con su ID asignado.
     * @throws pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception.ResourceNotFoundException Si el cliente o empleado no existe.
     * @throws IllegalStateException Si no hay stock suficiente para algún producto.
     */
    Venta registrarVenta(Venta venta);

    @Transactional(readOnly = true)
    List<Venta> listarVentas();

    @Transactional(readOnly = true)
    Venta obtenerVenta(Long id);

    /**
     * Lista todas las ventas registradas.
     * @return Una lista de todas las ventas.
     */
    List<Venta> listarTodos();

    /**
     * Busca una venta por su identificador único.
     * @param id El ID de la venta.
     * @return Un Optional que contiene la venta si existe.
     */
    Optional<Venta> buscarPorId(Long id);
}