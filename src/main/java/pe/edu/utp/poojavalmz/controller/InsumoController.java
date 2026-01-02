package pe.edu.utp.poojavalmz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.poojavalmz.model.Insumo;
import pe.edu.utp.poojavalmz.service.IInsumoService;

import java.util.List;


@RestController

@RequestMapping("/api/insumos")
public class InsumoController {


    @Autowired
    private IInsumoService insumoService;


    @GetMapping
    public ResponseEntity<List<Insumo>> listarInsumos() {
        List<Insumo> insumos = insumoService.listarTodos();
        return new ResponseEntity<>(insumos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Insumo> buscarInsumoPorId(@PathVariable Long id) {
        return insumoService.buscarPorId(id)
                .map(insumo -> new ResponseEntity<>(insumo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Insumo>> buscarInsumoPorProveedor(@PathVariable Long idProveedor) {
        List<Insumo> insumos = insumoService.buscarPorProveedor(idProveedor);
        if (insumos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(insumos, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Insumo> crearInsumo(@RequestBody Insumo insumo) {
        // El insumo.idInsumo debe ser null en la petición
        Insumo nuevoInsumo = insumoService.guardar(insumo);
        return new ResponseEntity<>(nuevoInsumo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insumo> actualizarInsumo(@PathVariable Long id, @RequestBody Insumo insumoDetalles) {
        return insumoService.buscarPorId(id).map(insumoExistente -> {


            insumoExistente.setNombre(insumoDetalles.getNombre());
            insumoExistente.setCostoUnitario(insumoDetalles.getCostoUnitario());



            Insumo insumoActualizado = insumoService.guardar(insumoExistente);
            return new ResponseEntity<>(insumoActualizado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInsumo(@PathVariable Long id) {
        return insumoService.buscarPorId(id).map(insumo -> {
            insumoService.eliminar(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
