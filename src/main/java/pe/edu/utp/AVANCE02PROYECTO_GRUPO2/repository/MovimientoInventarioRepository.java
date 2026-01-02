package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.MovimientoInventario;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
}
