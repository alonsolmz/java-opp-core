package pe.edu.utp.AVANCE02PROYECTO_GRUPO2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception.ResourceNotFoundException;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Cliente;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.DetalleVenta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Venta;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.VentaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IClienteService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IEmpleadoService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProductoService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl.VentaServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    // Inyección de la implementación del servicio que se va a probar
    @InjectMocks
    private VentaServiceImpl ventaService;

    // Mocks de las dependencias
    @Mock
    private VentaRepository ventaRepository;
    @Mock
    private IClienteService clienteService;
    @Mock
    private IEmpleadoService empleadoService;
    @Mock
    private IProductoService productoService;

    // Variables de prueba
    private Venta venta;
    private Cliente cliente;
    private Empleado empleado;
    private Producto producto1;
    private DetalleVenta detalle1;

    @BeforeEach
    void setUp() {
        // Inicialización de entidades simuladas
        cliente = new Cliente();
        cliente.setIdCliente(1L);

        empleado = new Empleado();
        empleado.setIdEmpleado(10L);

        producto1 = new Producto();
        producto1.setIdProducto(100L);
        producto1.setNombre("Zapato Formal Negro");
        producto1.setPrecio(new BigDecimal("150.00"));
        producto1.setStock(50); // Stock inicial

        detalle1 = new DetalleVenta();
        detalle1.setProducto(producto1);
        detalle1.setCantidad(2); // Cantidad a vender

        venta = new Venta();
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setDetalleVenta(Collections.singletonList(detalle1));
    }

    @Test
    void registrarVenta_Exito() {
        // Configuración de Mocks para el caso de éxito
        when(clienteService.buscarPorId(anyLong())).thenReturn(Optional.of(cliente));
        when(empleadoService.buscarPorId(anyLong())).thenReturn(Optional.of(empleado));
        when(productoService.buscarPorId(producto1.getIdProducto())).thenReturn(Optional.of(producto1));

        // Simulación: el repositorio devuelve la venta guardada
        when(ventaRepository.save(any(Venta.class))).thenAnswer(invocation -> {
            Venta v = invocation.getArgument(0);
            v.setIdVenta(1000L);
            return v;
        });

        // Ejecución de la prueba
        Venta ventaRegistrada = ventaService.registrarVenta(venta);

        // Verificaciones
        assertNotNull(ventaRegistrada);
        assertEquals(1000L, ventaRegistrada.getIdVenta());

        // Verificar que el stock se haya actualizado (50 - 2 = 48)
        assertEquals(48, producto1.getStock());

        // Verificar el total de la venta (150.00 * 2 = 300.00)
        assertEquals(new BigDecimal("300.00"), ventaRegistrada.getTotal());

        // Verificar que se haya llamado a guardar el producto con el nuevo stock
        verify(productoService, times(1)).guardar(producto1);
        // Verificar que se haya llamado a guardar la venta
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }


    @Test
    void registrarVenta_ClienteNoEncontrado_LanzaExcepcion() {
        // Configuración de Mocks: Cliente no encontrado
        when(clienteService.buscarPorId(anyLong())).thenReturn(Optional.empty());

        // Ejecución y verificación de excepción
        assertThrows(ResourceNotFoundException.class, () ->
                        ventaService.registrarVenta(venta),
                "Debería lanzar ResourceNotFoundException si el Cliente no existe"
        );

        // Verificar que el repositorio NO se haya llamado
        verify(ventaRepository, never()).save(any(Venta.class));
    }


    @Test
    void registrarVenta_EmpleadoNoEncontrado_LanzaExcepcion() {
        // Configuración de Mocks: Cliente OK, Empleado NO encontrado
        when(clienteService.buscarPorId(anyLong())).thenReturn(Optional.of(cliente));
        when(empleadoService.buscarPorId(anyLong())).thenReturn(Optional.empty());

        // Ejecución y verificación de excepción
        assertThrows(ResourceNotFoundException.class, () ->
                        ventaService.registrarVenta(venta),
                "Debería lanzar ResourceNotFoundException si el Empleado no existe"
        );

        // Verificar que el repositorio NO se haya llamado
        verify(ventaRepository, never()).save(any(Venta.class));
    }


    @Test
    void registrarVenta_StockInsuficiente_LanzaExcepcion() {
        // Establecer cantidad solicitada mayor que el stock (50 solicitados > 50 en stock)
        detalle1.setCantidad(51);

        // Configuración de Mocks: Cliente y Empleado OK, Producto OK
        when(clienteService.buscarPorId(anyLong())).thenReturn(Optional.of(cliente));
        when(empleadoService.buscarPorId(anyLong())).thenReturn(Optional.of(empleado));
        when(productoService.buscarPorId(producto1.getIdProducto())).thenReturn(Optional.of(producto1));

        // Ejecución y verificación de excepción
        assertThrows(IllegalStateException.class, () ->
                        ventaService.registrarVenta(venta),
                "Debería lanzar IllegalStateException si el stock es insuficiente"
        );

        // Verificar que el stock NO se haya modificado (no debería haber pasado la validación)
        assertEquals(50, producto1.getStock());

        // Verificar que NO se haya llamado a guardar el producto ni la venta
        verify(productoService, never()).guardar(any(Producto.class));
        verify(ventaRepository, never()).save(any(Venta.class));
    }


    @Test
    void listarTodos_DebeDevolverListaDeVentas() {
        // Datos de prueba
        Venta venta2 = new Venta();
        List<Venta> listaEsperada = Arrays.asList(venta, venta2);

        // Configuración de Mock
        when(ventaRepository.findAll()).thenReturn(listaEsperada);

        // Ejecución
        List<Venta> resultado = ventaService.listarTodos();

        // Verificación
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(ventaRepository, times(1)).findAll();
    }


    @Test
    void buscarPorId_VentaEncontrada_DebeDevolverOptionalConVenta() {
        // Establecer ID para la búsqueda
        venta.setIdVenta(50L);

        // Configuración de Mock
        when(ventaRepository.findById(50L)).thenReturn(Optional.of(venta));

        // Ejecución
        Optional<Venta> resultado = ventaService.buscarPorId(50L);

        // Verificación
        assertTrue(resultado.isPresent());
        assertEquals(50L, resultado.get().getIdVenta());
        verify(ventaRepository, times(1)).findById(50L);
    }
}