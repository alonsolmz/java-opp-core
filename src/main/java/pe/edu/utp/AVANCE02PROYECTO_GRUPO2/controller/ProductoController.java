package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    @Autowired
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

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


    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        // CORRECCIÓN: El service debe devolver List<Producto>
        List<Producto> productos = productoService.listarTodos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/marca")

    public ResponseEntity<List<Producto>> buscarPorMarca(@RequestParam String nombre) {
        // CORRECCIÓN: Llamar al método del service que busca por marca
        List<Producto> productos = productoService.buscarPorMarcaNombre(nombre);

        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Optional<Producto> productoExistente = productoService.buscarPorId(id);

        if (productoExistente.isPresent()) {
            Producto productoActualizar = productoExistente.get();


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);

        if (producto.isPresent()) {
            productoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
