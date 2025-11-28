package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.*;

// Interfaces de Repositorio.
// Las interfaces Cliente, Empleado, Producto, Proveedor y Venta
// han sido movidas a sus propios archivos con @Repository para un mejor manejo por Spring.

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}