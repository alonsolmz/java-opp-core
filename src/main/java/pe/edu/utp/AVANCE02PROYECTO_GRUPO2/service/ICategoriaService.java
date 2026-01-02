package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Categoria;

import java.util.List;
import java.util.Optional;


public interface ICategoriaService {

    Categoria guardar(Categoria categoria);
    List<Categoria> listarTodos();
    Optional<Categoria> buscarPorId(Long id);
    void eliminar(Long id);
}