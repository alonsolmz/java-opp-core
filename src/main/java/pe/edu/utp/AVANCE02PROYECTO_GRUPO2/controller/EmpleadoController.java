package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.guardar(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos() {
        List<Empleado> empleados = empleadoService.listarTodos();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.buscarPorId(id);
        return empleado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
        Optional<Empleado> empleadoExistente = empleadoService.buscarPorId(id);

        if (empleadoExistente.isPresent()) {
            Empleado empleadoActualizar = empleadoExistente.get();


            Empleado empleadoActualizado = empleadoService.guardar(empleadoActualizar);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


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
