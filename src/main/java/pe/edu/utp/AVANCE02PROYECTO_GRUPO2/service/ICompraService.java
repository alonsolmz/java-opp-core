package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.Util.EstadoCompra;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Compra;

import java.util.List;
import java.util.Optional;

public interface ICompraService {

    // Lógica Transaccional: Registrar la compra y actualizar el inventario.
    Compra registrarNuevaCompra(Compra compra);

    // CRUD Básico
    Optional<Compra> buscarPorId(Long id);
    List<Compra> listarTodas();

    // Lógica de Negocio Específica
    Compra actualizarEstadoCompra(Long id, EstadoCompra nuevoEstado);
}
