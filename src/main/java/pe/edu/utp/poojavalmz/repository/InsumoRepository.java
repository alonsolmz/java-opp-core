package pe.edu.utp.poojavalmz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pe.edu.utp.poojavalmz.model.*;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {


    List<Insumo> findByProveedorIdProveedor(Long idProveedor);


}