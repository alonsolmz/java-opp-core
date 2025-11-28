package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Marca;

import java.util.List;
import java.util.Optional;


 //Interfaz de la capa de servicio para gestionar la entidad Marca.

public interface IMarcaService {

    Marca guardar(Marca marca);
    List<Marca> listarTodos();
    Optional<Marca> buscarPorId(Long id);
    void eliminar(Long id);
}