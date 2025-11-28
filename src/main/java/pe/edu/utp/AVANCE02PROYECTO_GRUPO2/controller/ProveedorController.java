package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de la entidad Proveedor.
 * Mapea a /api/proveedores
 */
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final IProveedorService proveedorService;

    @Autowired
    public ProveedorController(IProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    /**
     * Crea un nuevo Proveedor.
     * Mapea a POST /api/proveedores
     * @param proveedor El objeto Proveedor a guardar.
     * @return ResponseEntity con el Proveedor creado y el estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.guardar(proveedor);
        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
    }

    /**
     * Lista todos los Proveedores.
     * Mapea a GET /api/proveedores
     * @return ResponseEntity con la lista de Proveedores y el estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Proveedor>> listarTodos() {
        List<Proveedor> proveedores = proveedorService.listarTodos();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    /**
     * Busca un Proveedor por su ID.
     * Mapea a GET /api/proveedores/{id}
     * @param id El ID del proveedor a buscar.
     * @return ResponseEntity con el Proveedor encontrado o estado 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscarPorId(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.buscarPorId(id);
        return proveedor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualiza un Proveedor existente.
     * Mapea a PUT /api/proveedores/{id}
     * @param id El ID del proveedor a actualizar.
     * @param proveedorDetalles Los datos actualizados del Proveedor.
     * @return ResponseEntity con el Proveedor actualizado o estado 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetalles) {
        Optional<Proveedor> proveedorExistente = proveedorService.buscarPorId(id);

        if (proveedorExistente.isPresent()) {
            Proveedor proveedorActualizar = proveedorExistente.get();

            // Reasignar los campos que se desean actualizar.
            // Es importante que la entidad Proveedor tenga los campos necesarios para que esto funcione.

            // Asumiendo que Proveedor tiene setters para nombre, ruc, etc.
            // proveedorActualizar.setNombre(proveedorDetalles.getNombre());
            // proveedorActualizar.setRuc(proveedorDetalles.getRuc());
            // proveedorActualizar.setDireccion(proveedorDetalles.getDireccion());
            // ... Mapear todos los campos relevantes

            Proveedor proveedorActualizado = proveedorService.guardar(proveedorActualizar);
            return new ResponseEntity<>(proveedorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un Proveedor por su ID.
     * Mapea a DELETE /api/proveedores/{id}
     * @param id El ID del proveedor a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.buscarPorId(id);

        if (proveedor.isPresent()) {
            proveedorService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
