package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import java.util.List;
import java.util.Optional;


public interface IVentaService {


    Venta registrarVenta(Venta venta);

    @Transactional(readOnly = true)
    List<Venta> listarVentas();

    @Transactional(readOnly = true)
    Venta obtenerVenta(Long id);


    List<Venta> listarTodos();

    Optional<Venta> buscarPorId(Long id);
}