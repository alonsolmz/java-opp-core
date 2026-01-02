package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception.ResourceNotFoundException;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IVentaService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Gestión de transacciones de venta e inventario")
public class VentaController {

    private final IVentaService ventaService;


    @Operation(summary = "Registrar una nueva venta y actualizar el stock de productos")
    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.registrarVenta(venta);

            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (ResourceNotFoundException | IllegalStateException e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Obtener una lista de todas las ventas")
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }


    @Operation(summary = "Obtener una venta por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable Long id) {
        try {
            Venta venta = ventaService.obtenerVenta(id);
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}