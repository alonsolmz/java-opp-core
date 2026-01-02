package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface IProductoService {

    @Transactional
    Producto guardar(Producto producto); // <--- CORREGIDO: Recibe y devuelve Producto

    @Transactional(readOnly = true)
    List<Producto> listarTodos();


    @Transactional(readOnly = true)
    Optional<Producto> buscarPorId(Long id);
    // Método para buscar por Marca (nombre, no ID)
    @Transactional(readOnly = true)
    List<Producto> buscarPorMarcaNombre(String nombreMarca);
    List<Producto> buscarPorMarca(String marcaNombre);

    @Transactional
    void eliminar(Long id);
}