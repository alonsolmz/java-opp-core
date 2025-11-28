package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;

import java.util.List;

public interface IProveedorService {
    Proveedor guardar(Proveedor proveedor);
    List<Proveedor> listarTodos();
    Proveedor buscarPorId(Long id);
    void eliminar(Long id);
}