package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
