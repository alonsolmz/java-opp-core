package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Marca;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de la entidad Marca.
 * Mapea a /api/marcas
 */
@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final IMarcaService marcaService;

    @Autowired
    public MarcaController(IMarcaService marcaService) {
        this.marcaService = marcaService;
    }

    /**
     * Crea una nueva Marca.
     * Mapea a POST /api/marcas
     * @param marca El objeto Marca a guardar.
     * @return ResponseEntity con la Marca creada y el estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Marca> crearMarca(@RequestBody Marca marca) {
        Marca nuevaMarca = marcaService.guardar(marca);
        return new ResponseEntity<>(nuevaMarca, HttpStatus.CREATED);
    }

    /**
     * Lista todas las Marcas.
     * Mapea a GET /api/marcas
     * @return ResponseEntity con la lista de Marcas y el estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Marca>> listarTodos() {
        List<Marca> marcas = marcaService.listarTodos();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    /**
     * Busca una Marca por su ID.
     * Mapea a GET /api/marcas/{id}
     * @param id El ID de la marca a buscar.
     * @return ResponseEntity con la Marca encontrada o estado 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        Optional<Marca> marca = marcaService.buscarPorId(id);
        return marca.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualiza una Marca existente.
     * Mapea a PUT /api/marcas/{id}
     * @param id El ID de la marca a actualizar.
     * @param marcaDetalles Los datos actualizados de la Marca.
     * @return ResponseEntity con la Marca actualizada o estado 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marcaDetalles) {
        Optional<Marca> marcaExistente = marcaService.buscarPorId(id);

        if (marcaExistente.isPresent()) {
            Marca marcaActualizar = marcaExistente.get();

            // Solo actualizamos los campos modificables
            marcaActualizar.setNombre(marcaDetalles.getNombre());
            marcaActualizar.setDescripcion(marcaDetalles.getDescripcion());

            Marca marcaActualizada = marcaService.guardar(marcaActualizar);
            return new ResponseEntity<>(marcaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una Marca por su ID.
     * Mapea a DELETE /api/marcas/{id}
     * @param id El ID de la marca a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content).
     */
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
