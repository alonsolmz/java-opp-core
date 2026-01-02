package pe.edu.utp.poojavalmz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.poojavalmz.model.*;


@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
