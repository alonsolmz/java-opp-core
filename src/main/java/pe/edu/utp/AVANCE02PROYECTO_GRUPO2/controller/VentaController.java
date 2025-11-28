package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IVentaService;

import java.util.List;

/**
 * Controlador REST para la gestión de Ventas.
 * Define los endpoints para registrar, listar y obtener ventas.
 */
@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Gestión de transacciones de venta e inventario")
public class VentaController {

    private final IVentaService ventaService;

    /**
     * Registra una nueva venta.
     * Al recibir la Venta con sus detalles, el servicio actualiza el stock de productos.
     * @param venta El objeto Venta a registrar.
     * @return La venta registrada con su ID y estado 201 Created.
     */
    @Operation(summary = "Registrar una nueva venta y actualizar el stock de productos")
    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.registrarVenta(venta);
            // Retorna 201 CREATED
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (ResourceNotFoundException | IllegalStateException e) {
            // Manejo específico de errores de negocio y stock
            // En un entorno real, se usaría un @ControllerAdvice para manejar excepciones globalmente.
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Lista todas las ventas registradas en el sistema.
     * @return Una lista de todas las ventas.
     */
    @Operation(summary = "Obtener una lista de todas las ventas")
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    /**
     * Busca una venta específica por su ID.
     * @param id ID de la venta a buscar.
     * @return La venta encontrada o 404 Not Found.
     */
    @Operation(summary = "Obtener una venta por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVenta(@PathVariable Long id) {
        try {
            Venta venta = ventaService.obtenerVenta(id);
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            // Retorna 404 NOT FOUND si la venta no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}