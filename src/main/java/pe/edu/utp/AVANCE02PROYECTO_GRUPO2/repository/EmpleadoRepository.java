package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz Repositorio para la entidad Empleado.
 * Proporciona métodos CRUD básicos y permite definir consultas personalizadas.
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    /**
     * Busca un Empleado por su número de DNI.
     * Asumimos que la entidad Empleado tiene un campo 'dni'.
     * @param dni El DNI del empleado a buscar.
     * @return Un Optional que contiene el Empleado si se encuentra.
     */
    Optional<Empleado> findByDni(String dni);
}