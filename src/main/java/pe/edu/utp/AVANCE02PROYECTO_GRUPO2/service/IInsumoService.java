package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Insumo;
import java.util.List;
import java.util.Optional;

public interface IInsumoService {
    Insumo guardar(Insumo insumo);
    Optional<Insumo> buscarPorId(Long id);
    List<Insumo> listarTodos();
    void eliminar(Long id);


    List<Insumo> buscarPorProveedor(Long idProveedor);
}