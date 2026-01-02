package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.*;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Optional<Marca> findByNombre(String nombre);

}