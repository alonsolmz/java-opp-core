package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.*;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    Stock actualizarStock(Long idStock, Integer nuevaCantidad);
    Optional<Stock> buscarStockPorProductoTallaColor(Long idProducto, String talla, Long idColor);
}
