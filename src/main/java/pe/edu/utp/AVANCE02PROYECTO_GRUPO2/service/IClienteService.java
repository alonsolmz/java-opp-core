package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Cliente;

import java.util.List;
import java.util.Optional;


public interface IClienteService {

    Cliente guardar(Cliente cliente);
    List<Cliente> listarTodos();
    Optional<Cliente> buscarPorId(Long id);
    void eliminar(Long id);
}