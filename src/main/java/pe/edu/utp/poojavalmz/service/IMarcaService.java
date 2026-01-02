package pe.edu.utp.poojavalmz.service;

import pe.edu.utp.poojavalmz.model.Marca;

import java.util.List;
import java.util.Optional;


 //Interfaz de la capa de servicio para gestionar la entidad Marca.

public interface IMarcaService {

    Marca guardar(Marca marca);
    List<Marca> listarTodos();
    Optional<Marca> buscarPorId(Long id);
    void eliminar(Long id);
}