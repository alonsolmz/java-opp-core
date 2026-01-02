package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.Util.EstadoCompra;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Compra;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.ICompraService;

import java.util.List;

@RestController
@RequestMapping("/api/compras") // 2. Mapea la URL base
public class CompraController {

    private final ICompraService compraService;

    public CompraController(ICompraService compraService) {
        this.compraService = compraService;
    }


    @PostMapping
    public ResponseEntity<Compra> crearCompra(@RequestBody Compra compra) {

        Compra nuevaCompra = compraService.registrarNuevaCompra(compra);

        return new ResponseEntity<>(nuevaCompra, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Compra>> listarTodas() {

        List<Compra> compras = compraService.listarTodas();
        return ResponseEntity.ok(compras);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerPorId(@PathVariable Long id) {

        return compraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<Compra> actualizarEstado(
            @PathVariable Long id,
            @PathVariable EstadoCompra nuevoEstado) {

        try {
            Compra compraActualizada = compraService.actualizarEstadoCompra(id, nuevoEstado);
            return ResponseEntity.ok(compraActualizada);
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().build();
        }
    }
}
