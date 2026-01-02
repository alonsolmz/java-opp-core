package pe.edu.utp.poojavalmz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.poojavalmz.model.Proveedor;
import pe.edu.utp.poojavalmz.service.IProveedorService;
import java.util.List;


@RestController

@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final IProveedorService proveedorService;


    @Autowired
    public ProveedorController(IProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }


    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor nuevoProveedor = proveedorService.guardar(proveedor);

            return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<Proveedor>> listarTodos() {
        List<Proveedor> proveedores = proveedorService.listarTodos();
        return ResponseEntity.ok(proveedores);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {

        return proveedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetalles) {

        return proveedorService.buscarPorId(id)
                .map(proveedorExistente -> {

                    proveedorExistente.setRuc(proveedorDetalles.getRuc());
                    proveedorExistente.setNombre(proveedorDetalles.getNombre());
                    proveedorExistente.setDireccion(proveedorDetalles.getDireccion());


                    Proveedor proveedorActualizado = proveedorService.guardar(proveedorExistente);
                    return new ResponseEntity<>(proveedorActualizado, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        try {

            proveedorService.eliminar(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}