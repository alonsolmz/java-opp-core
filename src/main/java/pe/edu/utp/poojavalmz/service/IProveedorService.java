package pe.edu.utp.poojavalmz.service;

import pe.edu.utp.poojavalmz.model.Proveedor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface IProveedorService {

    @Transactional
    Proveedor guardar(Proveedor proveedor);

    @Transactional(readOnly = true)
    List<Proveedor> listarTodos();

    @Transactional(readOnly = true)
    Optional<Proveedor> buscarPorId(Long id);

    @Transactional
    void eliminar(Long id);
}