package pe.edu.utp.poojavalmz.repository;

import pe.edu.utp.poojavalmz.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {


    Optional<Empleado> findByDni(String dni);
}