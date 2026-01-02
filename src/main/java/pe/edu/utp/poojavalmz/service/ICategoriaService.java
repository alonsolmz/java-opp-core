package pe.edu.utp.poojavalmz.service;

import pe.edu.utp.poojavalmz.model.Categoria;

import java.util.List;
import java.util.Optional;


public interface ICategoriaService {

    Categoria guardar(Categoria categoria);
    List<Categoria> listarTodos();
    Optional<Categoria> buscarPorId(Long id);
    void eliminar(Long id);
}