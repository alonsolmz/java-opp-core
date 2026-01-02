package pe.edu.utp.poojavalmz.service;

import pe.edu.utp.poojavalmz.model.Empleado;

import java.util.List;
import java.util.Optional;


public interface IEmpleadoService {

    Empleado guardar(Empleado empleado);
    List<Empleado> listarTodos();
    Optional<Empleado> buscarPorId(Long id);
    void eliminar(Long id);
}
