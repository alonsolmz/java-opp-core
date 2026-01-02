package pe.edu.utp.poojavalmz.service.impl;

import jakarta.transaction.Transactional;
import pe.edu.utp.poojavalmz.model.Color;
import pe.edu.utp.poojavalmz.model.Insumo;
import pe.edu.utp.poojavalmz.model.Producto;
import pe.edu.utp.poojavalmz.model.Stock;
import pe.edu.utp.poojavalmz.repository.StockRepository;
import org.springframework.stereotype.Service;
import pe.edu.utp.poojavalmz.service.IStockService;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;


    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }



    @Override
    public List<Stock> listarTodo() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> buscarPorId(Long idStock) {
        return stockRepository.findById(idStock);
    }

    @Override
    public Optional<Stock> buscarStockPorInsumoId(Long idInsumo) {



        return Optional.empty();
    }

    @Override
    public List<Stock> listarStockBajo(Integer cantidadMinima) {
        return stockRepository.findByCantidadLessThan(cantidadMinima);
    }



    @Override
    @Transactional
    public Stock actualizarStockPorInsumo(Insumo insumo, Integer cantidadAñadida) {

        Optional<Stock> stockExistente = stockRepository.findByInsumo(insumo);
        Stock stock;

        if (stockExistente.isPresent()) {
            stock = stockExistente.get();

            stock.setCantidad(stock.getCantidad() + cantidadAñadida);
        } else {

            stock = new Stock();
            stock.setInsumo(insumo);
            stock.setCantidad(cantidadAñadida);

            stock.setProducto(null);
            stock.setTalla(null);
            stock.setColor(null);
        }

        return stockRepository.save(stock);
    }

    @Override
    public Stock reducirStockDeProducto(Producto producto, String talla, Color color, Integer cantidadVendida) {
        return null;
    }


}