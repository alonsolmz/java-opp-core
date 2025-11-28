package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de la capa de servicio para gestionar la entidad Empleado.
 */
public interface IEmpleadoService {

    Empleado guardar(Empleado empleado);
    List<Empleado> listarTodos();
    Optional<Empleado> buscarPorId(Long id);
    void eliminar(Long id);
}
