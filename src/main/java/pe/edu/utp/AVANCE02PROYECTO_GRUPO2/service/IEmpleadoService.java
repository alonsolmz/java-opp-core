package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;

import java.util.List;
import java.util.Optional;


public interface IEmpleadoService {

    Empleado guardar(Empleado empleado);
    List<Empleado> listarTodos();
    Optional<Empleado> buscarPorId(Long id);
    void eliminar(Long id);
}
