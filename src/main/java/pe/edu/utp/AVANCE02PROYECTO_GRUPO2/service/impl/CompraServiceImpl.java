package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.Util.EstadoCompra;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Compra;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Insumo;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.ItemCompra;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.CompraRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.ICompraService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IStockService;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements ICompraService {

    private final CompraRepository compraRepository;
    private final IStockService stockService;


    public CompraServiceImpl(CompraRepository compraRepository, IStockService stockService) {
        this.compraRepository = compraRepository;
        this.stockService = stockService;
    }

    @Override
    @Transactional
    public Compra registrarNuevaCompra(Compra compra) {


        Compra compraGuardada = compraRepository.save(compra);


        if (compraGuardada.getEstado() == EstadoCompra.RECIBIDA) {
            actualizarStockTrasRecepcion(compraGuardada);
        }

        return compraGuardada;
    }

    @Override
    public Optional<Compra> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Compra> listarTodas() {
        return List.of();
    }

    @Override
    public Compra actualizarEstadoCompra(Long id, EstadoCompra nuevoEstado) {
        return null;
    }

    private void actualizarStockTrasRecepcion(Compra compra) {
        // Iterar sobre todos los ItemCompra
        for (ItemCompra item : compra.getItems()) {


            Insumo insumo = item.getInsumo();
            Integer cantidad = item.getCantidad();


            stockService.actualizarStockPorInsumo(insumo, cantidad);


        }
    }


}