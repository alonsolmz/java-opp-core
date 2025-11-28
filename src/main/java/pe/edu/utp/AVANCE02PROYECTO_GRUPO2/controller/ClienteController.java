package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.controller;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Cliente;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de la entidad Cliente.
 * Mapea a /api/clientes
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    @Autowired
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Crea un nuevo Cliente.
     * Mapea a POST /api/clientes
     * @param cliente El objeto Cliente a guardar.
     * @return ResponseEntity con el Cliente creado y el estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardar(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    /**
     * Lista todos los Clientes.
     * Mapea a GET /api/clientes
     * @return ResponseEntity con la lista de Clientes y el estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * Busca un Cliente por su ID.
     * Mapea a GET /api/clientes/{id}
     * @param id El ID del cliente a buscar.
     * @return ResponseEntity con el Cliente encontrado o estado 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualiza un Cliente existente.
     * Mapea a PUT /api/clientes/{id}
     * @param id El ID del cliente a actualizar.
     * @param clienteDetalles Los datos actualizados del Cliente.
     * @return ResponseEntity con el Cliente actualizado o estado 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalles) {
        Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);

        if (clienteExistente.isPresent()) {
            Cliente clienteActualizar = clienteExistente.get();

            // Mapeamos los campos que queremos actualizar
            clienteActualizar.setNombre(clienteDetalles.getNombre());
            // Asumiendo campos como DNI, dirección, teléfono, etc.
            // clienteActualizar.setDni(clienteDetalles.getDni());
            // clienteActualizar.setDireccion(clienteDetalles.getDireccion());

            Cliente clienteActualizado = clienteService.guardar(clienteActualizar);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un Cliente por su ID.
     * Mapea a DELETE /api/clientes/{id}
     * @param id El ID del cliente a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);

        if (cliente.isPresent()) {
            clienteService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}