package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.*;
import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Optional<Marca> findByNombre(String nombre);

}