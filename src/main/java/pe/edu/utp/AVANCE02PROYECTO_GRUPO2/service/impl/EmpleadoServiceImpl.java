package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.EmpleadoRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz IEmpleadoService.
 * Contiene la lógica de negocio para la gestión de Empleados.
 */
@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Guarda un nuevo empleado o actualiza uno existente.
     * @param empleado El objeto Empleado a guardar.
     * @return El empleado guardado.
     */
    @Transactional
    @Override
    public Empleado guardar(Empleado empleado) {
        // Lógica de negocio: se podría añadir validación para DNI único, roles, o salario.
        return empleadoRepository.save(empleado);
    }

    /**
     * Lista todos los empleados.
     * @return Una lista de todos los empleados.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    /**
     * Busca un empleado por su identificador único.
     * @param id El ID del empleado.
     * @return Un Optional que contiene el empleado si existe.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    /**
     * Elimina un empleado por su ID.
     * @param id El ID del empleado a eliminar.
     */
    @Transactional
    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
