package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de la entidad Producto.
 * Expone endpoints para las operaciones CRUD y búsquedas específicas.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    @Autowired
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Endpoint para crear un nuevo Producto.
     * Mapea a POST /api/productos
     * @param producto El objeto Producto a guardar, validado por @RequestBody.
     * @return ResponseEntity con el Producto creado y el estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.guardar(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación de negocio (ej. precio negativo)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint para listar todos los Productos.
     * Mapea a GET /api/productos
     * @return ResponseEntity con la lista de Productos y el estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        List<Producto> productos = productoService.listarTodos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar un Producto por su ID.
     * Mapea a GET /api/productos/{id}
     * @param id El ID del producto a buscar.
     * @return ResponseEntity con el Producto encontrado o estado 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint para buscar Productos por el nombre de la Marca.
     * Mapea a GET /api/productos/marca?nombre={nombreMarca}
     * @param nombre El nombre de la marca.
     * @return ResponseEntity con la lista de productos de esa marca.
     */
    @GetMapping("/marca")
    public ResponseEntity<List<Producto>> buscarPorMarca(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorMarca(nombre);

        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si la lista está vacía
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /**
     * Endpoint para actualizar un Producto existente.
     * Mapea a PUT /api/productos/{id}
     * @param id El ID del producto a actualizar.
     * @param productoDetalles Los datos actualizados del Producto.
     * @return ResponseEntity con el Producto actualizado o estado 404/400.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Optional<Producto> productoExistente = productoService.buscarPorId(id);

        if (productoExistente.isPresent()) {
            Producto productoActualizar = productoExistente.get();

            // Aquí copiamos los nuevos valores al objeto existente
            productoActualizar.setNombre(productoDetalles.getNombre());
            productoActualizar.setPrecio(productoDetalles.getPrecio());
            productoActualizar.setStock(productoDetalles.getStock());
            productoActualizar.setCategoria(productoDetalles.getCategoria());
            productoActualizar.setProveedor(productoDetalles.getProveedor());
            productoActualizar.setMarca(productoDetalles.getMarca());

            try {
                Producto productoActualizado = productoService.guardar(productoActualizar);
                return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Error de validación
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para eliminar un Producto por su ID.
     * Mapea a DELETE /api/productos/{id}
     * @param id El ID del producto a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);

        if (producto.isPresent()) {
            productoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 indica éxito sin cuerpo de respuesta
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
