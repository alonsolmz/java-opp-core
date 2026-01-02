package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Insumo;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Stock;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {


    Optional<Stock> findByInsumo(Insumo insumo);


    Optional<Stock> findByProductoAndTallaAndColor(Producto producto, String talla, Color color);


    List<Stock> findByCantidadLessThan(Integer cantidad);
}