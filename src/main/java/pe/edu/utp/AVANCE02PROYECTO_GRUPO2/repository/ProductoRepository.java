package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.*;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
