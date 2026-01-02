package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.*;
import java.util.List;
import java.util.Optional;

public interface IStockService {




    Stock actualizarStockPorInsumo(Insumo insumo, Integer cantidadAñadida);


    Stock reducirStockDeProducto(Producto producto, String talla, Color color, Integer cantidadVendida);

    List<Stock> listarTodo();


    Optional<Stock> buscarPorId(Long idStock);


    Optional<Stock> buscarStockPorInsumoId(Long idInsumo);

    List<Stock> listarStockBajo(Integer cantidadMinima);
}