package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de la entidad Empleado.
 * Mapea a /api/empleados
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Crea un nuevo Empleado.
     * Mapea a POST /api/empleados
     * @param empleado El objeto Empleado a guardar.
     * @return ResponseEntity con el Empleado creado y el estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.guardar(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    /**
     * Lista todos los Empleados.
     * Mapea a GET /api/empleados
     * @return ResponseEntity con la lista de Empleados y el estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos() {
        List<Empleado> empleados = empleadoService.listarTodos();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    /**
     * Busca un Empleado por su ID.
     * Mapea a GET /api/empleados/{id}
     * @param id El ID del empleado a buscar.
     * @return ResponseEntity con el Empleado encontrado o estado 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.buscarPorId(id);
        return empleado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualiza un Empleado existente.
     * Mapea a PUT /api/empleados/{id}
     * @param id El ID del empleado a actualizar.
     * @param empleadoDetalles Los datos actualizados del Empleado.
     * @return ResponseEntity con el Empleado actualizado o estado 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
        Optional<Empleado> empleadoExistente = empleadoService.buscarPorId(id);

        if (empleadoExistente.isPresent()) {
            Empleado empleadoActualizar = empleadoExistente.get();

            // Mapeamos los campos que queremos actualizar
            empleadoActualizar.setNombre(empleadoDetalles.getNombre());
            // Asumiendo campos como DNI, cargo, salario, etc.
            // empleadoActualizar.setDni(empleadoDetalles.getDni());
            // empleadoActualizar.setCargo(empleadoDetalles.getCargo());

            Empleado empleadoActualizado = empleadoService.guardar(empleadoActualizar);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un Empleado por su ID.
     * Mapea a DELETE /api/empleados/{id}
     * @param id El ID del empleado a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.buscarPorId(id);

        if (empleado.isPresent()) {
            empleadoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
