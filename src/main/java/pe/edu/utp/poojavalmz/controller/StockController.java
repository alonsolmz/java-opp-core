package pe.edu.utp.poojavalmz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.poojavalmz.model.Stock;
import pe.edu.utp.poojavalmz.service.IStockService;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final IStockService stockService;

    public StockController(IStockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping
    public ResponseEntity<List<Stock>> listarTodoElStock() {
        List<Stock> stockTotal = stockService.listarTodo();
        return ResponseEntity.ok(stockTotal);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Stock> obtenerStockPorId(@PathVariable Long id) {
        return stockService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/insumo/{insumoId}")
    public ResponseEntity<Stock> obtenerStockPorInsumo(@PathVariable Long insumoId) {

        return stockService.buscarStockPorInsumoId(insumoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/alerta")
    public ResponseEntity<List<Stock>> listarStockBajo(@RequestParam(defaultValue = "10") Integer cantidadMinima) {

        List<Stock> alertas = stockService.listarStockBajo(cantidadMinima);
        return ResponseEntity.ok(alertas);
    }


}