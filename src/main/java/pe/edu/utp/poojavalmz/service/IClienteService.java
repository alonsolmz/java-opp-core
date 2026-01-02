package pe.edu.utp.poojavalmz.service;

import pe.edu.utp.poojavalmz.model.Cliente;

import java.util.List;
import java.util.Optional;


public interface IClienteService {

    Cliente guardar(Cliente cliente);
    List<Cliente> listarTodos();
    Optional<Cliente> buscarPorId(Long id);
    void eliminar(Long id);
}