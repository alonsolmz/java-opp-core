package pe.edu.utp.poojavalmz.service;

import pe.edu.utp.poojavalmz.model.Insumo;
import java.util.List;
import java.util.Optional;

public interface IInsumoService {
    Insumo guardar(Insumo insumo);
    Optional<Insumo> buscarPorId(Long id);
    List<Insumo> listarTodos();
    void eliminar(Long id);


    List<Insumo> buscarPorProveedor(Long idProveedor);
}