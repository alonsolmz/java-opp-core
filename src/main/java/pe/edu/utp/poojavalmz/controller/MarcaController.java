package pe.edu.utp.poojavalmz.controller;

import pe.edu.utp.poojavalmz.model.Marca;
import pe.edu.utp.poojavalmz.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final IMarcaService marcaService;

    @Autowired
    public MarcaController(IMarcaService marcaService) {
        this.marcaService = marcaService;
    }


    @PostMapping
    public ResponseEntity<Marca> crearMarca(@RequestBody Marca marca) {
        Marca nuevaMarca = marcaService.guardar(marca);
        return new ResponseEntity<>(nuevaMarca, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Marca>> listarTodos() {
        List<Marca> marcas = marcaService.listarTodos();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        Optional<Marca> marca = marcaService.buscarPorId(id);
        return marca.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marcaDetalles) {
        Optional<Marca> marcaExistente = marcaService.buscarPorId(id);

        if (marcaExistente.isPresent()) {
            Marca marcaActualizar = marcaExistente.get();

            marcaActualizar.setNombre(marcaDetalles.getNombre());
            marcaActualizar.setDescripcion(marcaDetalles.getDescripcion());

            Marca marcaActualizada = marcaService.guardar(marcaActualizar);
            return new ResponseEntity<>(marcaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        Optional<Marca> marca = marcaService.buscarPorId(id);

        if (marca.isPresent()) {
            marcaService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
