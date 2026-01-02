package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception.ResourceNotFoundException;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.DetalleVenta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.VentaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements IVentaService {

    private final VentaRepository ventaRepository;
    private final IProductoService productoService;
    private final IClienteService clienteService;
    private final IEmpleadoService empleadoService;

    private static final int DECIMALES = 2;

    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {


        for (DetalleVenta detalle : venta.getDetalleVenta()) {


            Long idProducto = detalle.getProducto().getIdProducto();

            Producto prod = productoService.buscarPorId(idProducto) // Devuelve Optional<Producto>
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + idProducto));



            Integer cantidadVendida = detalle.getCantidad();

            if (prod.getStock() < cantidadVendida) {
                throw new IllegalStateException("Stock insuficiente para el Producto: " + prod.getNombre());
            }


            prod.setStock(prod.getStock() - cantidadVendida);


            productoService.guardar(prod);


        }




        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Venta obtenerVenta(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
    }

    @Override
    public List<Venta> listarTodos() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }
}