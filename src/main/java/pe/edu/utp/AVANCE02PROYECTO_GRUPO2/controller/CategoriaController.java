package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Categoria;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de la entidad Categoria.
 * Mapea a /api/categorias
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Crea una nueva Categoria.
     * Mapea a POST /api/categorias
     * @param categoria El objeto Categoria a guardar.
     * @return ResponseEntity con la Categoria creada y el estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.guardar(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    /**
     * Lista todas las Categorias.
     * Mapea a GET /api/categorias
     * @return ResponseEntity con la lista de Categorias y el estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos() {
        List<Categoria> categorias = categoriaService.listarTodos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Busca una Categoria por su ID.
     * Mapea a GET /api/categorias/{id}
     * @param id El ID de la categoría a buscar.
     * @return ResponseEntity con la Categoria encontrada o estado 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualiza una Categoria existente.
     * Mapea a PUT /api/categorias/{id}
     * @param id El ID de la categoría a actualizar.
     * @param categoriaDetalles Los datos actualizados de la Categoria.
     * @return ResponseEntity con la Categoria actualizada o estado 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetalles) {
        Optional<Categoria> categoriaExistente = categoriaService.buscarPorId(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoriaActualizar = categoriaExistente.get();

            // Solo actualizamos el nombre
            categoriaActualizar.setNombre(categoriaDetalles.getNombre());

            Categoria categoriaActualizada = categoriaService.guardar(categoriaActualizar);
            return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una Categoria por su ID.
     * Mapea a DELETE /api/categorias/{id}
     * @param id El ID de la categoría a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);

        if (categoria.isPresent()) {
            categoriaService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}