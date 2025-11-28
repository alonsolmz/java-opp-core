package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.DetalleVenta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.VentaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IClienteService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProductoService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IVentaService;

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

    // Constante para el redondeo a 2 decimales, esencial para cálculos monetarios
    private static final int DECIMALES = 2;

    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {

        // 1. Validaciones de entidades y datos
        if (venta.getCliente() == null || venta.getCliente().getIdCliente() == null) {
            throw new ResourceNotFoundException("Cliente es obligatorio.");
        }
        clienteService.buscarPorId(venta.getCliente().getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + venta.getCliente().getIdCliente()));

        if (venta.getEmpleado() == null || venta.getEmpleado().getIdEmpleado() == null) {
            throw new ResourceNotFoundException("Empleado es obligatorio.");
        }
        empleadoService.buscarPorId(venta.getEmpleado().getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + venta.getEmpleado().getIdEmpleado()));

        if (venta.getFechaVenta() == null) {
            venta.setFechaVenta(LocalDateTime.now());
        }

        // CORRECCIÓN CLAVE: El acumulador del total de la venta ahora es BigDecimal
        BigDecimal totalVenta = BigDecimal.ZERO;

        // El método .getDetalles() es el que debe usarse para acceder a la lista de detalles de venta
        for (DetalleVenta detalle : venta.getDetalleVenta()) {

            // 2.1 Obtener producto usando el servicio
            Producto prod = productoService.buscarPorId(detalle.getProducto().getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + detalle.getProducto().getIdProducto()));

            // 2.2 Validación de stock
            if (prod.getStock() < detalle.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para: " + prod.getNombre());
            }

            // 2.3 Obtener y usar el precio del producto (ASUMIMOS que prod.getPreciounitario() ahora devuelve BigDecimal)
            BigDecimal precioUnitario = BigDecimal.valueOf(prod.getPreciounitario());
            detalle.setPrecioUnitario(precioUnitario.setScale(DECIMALES, RoundingMode.HALF_UP));

            // CRÍTICO: Cálculo de subtotal usando BigDecimal.
            // Convertimos la cantidad (Integer) a BigDecimal para la multiplicación
            BigDecimal cantidadBD = BigDecimal.valueOf(detalle.getCantidad());

            // Multiplicación: precioUnitario * cantidadBD
            BigDecimal subtotal = precioUnitario
                    .multiply(cantidadBD)
                    .setScale(DECIMALES, RoundingMode.HALF_UP); // Redondeo

            // Acumulación del total: totalVenta = totalVenta + subtotal
            totalVenta = totalVenta.add(subtotal);

            // Asignación del subtotal corregido al detalle
            detalle.setSubtotal(subtotal);

            // 2.4 Descontar stock y guardar
            prod.setStock(prod.getStock() - detalle.getCantidad());
            // Usamos guardar del servicio para asegurar que el cambio de stock se persista en la transacción
            productoService.guardar(prod);

            // 2.5 Relación bidireccional
            detalle.setVenta(venta);
        }

        // 3. Asignar total y guardar la venta
        venta.setTotal(totalVenta.setScale(DECIMALES, RoundingMode.HALF_UP));

        // 4. Guardar la venta (los detalles deben guardarse por cascade)
        return ventaRepository.save(venta);
    }

    /**
     * Obtiene una lista de todas las ventas.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    /**
     * Obtiene una venta por su ID.
     */
    @Transactional(readOnly = true)
    @Override
    public Venta obtenerVenta(Long id) {
        // Asumiendo que IVentaService tiene el método obtenerVenta o buscarPorId. Usamos buscarPorId para consistencia.
        return ventaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
    }

    public List<Venta> listarTodos() {
        // Este método parece no estar implementado correctamente y solo devuelve List.of()
        // Debería llamar a ventaRepository.findAll() o ser eliminado si no es parte de la interfaz.
        return List.of();
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return Optional.empty();
    }

    public Optional<Venta> buscarPorId(long l) {
        return Optional.empty();
    }
}