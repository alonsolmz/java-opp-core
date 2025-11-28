package pe.edu.utp.AVANCE02PROYECTO_GRUPO2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ProductoRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl.ProductoServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase ProductoServiceImpl.
 * Utiliza Mockito para simular la capa de persistencia (ProductoRepository).
 */
@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    // Simula la dependencia del repositorio
    @Mock
    private ProductoRepository productoRepository;

    // Inyecta los mocks en la clase a probar
    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    void setUp() {
        // Inicializa objetos Producto de ejemplo para usar en las pruebas
        producto1 = new Producto();
        // CORRECCIÓN: Usar setIdProducto() en lugar de setId()
        producto1.setIdProducto(1L);
        producto1.setNombre("Zapatilla Deportiva");
        producto1.setPrecio(150.0);
        producto1.setStock(100);

        producto2 = new Producto();
        // CORRECCIÓN: Usar setIdProducto() en lugar de setId()
        producto2.setIdProducto(2L);
        producto2.setNombre("Botín Casual");
        producto2.setPrecio(200.0);
        producto2.setStock(50);
    }

    @Test
    void testGuardarProducto() {
        // Configuración del mock: cuando se llame a save con producto1, debe devolver producto1
        when(productoRepository.save(producto1)).thenReturn(producto1);

        // Ejecución del método a probar
        Producto productoGuardado = productoService.guardar(producto1);

        // Verificación: comprueba que el objeto devuelto no sea nulo y que sea el esperado
        assertNotNull(productoGuardado);
        assertEquals("Zapatilla Deportiva", productoGuardado.getNombre());

        // Verifica que el método save del repositorio haya sido llamado exactamente una vez
        verify(productoRepository, times(1)).save(producto1);
    }

    @Test
    void testListarTodos() {
        // Configuración del mock: cuando se llame a findAll, debe devolver la lista de productos
        List<Producto> listaEsperada = Arrays.asList(producto1, producto2);
        when(productoRepository.findAll()).thenReturn(listaEsperada);

        // Ejecución
        List<Producto> productos = productoService.listarTodos();

        // Verificación
        assertNotNull(productos);
        assertEquals(2, productos.size());
        assertEquals("Botín Casual", productos.get(1).getNombre());

        // Verifica que el método findAll del repositorio haya sido llamado
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdEncontrado() {
        Long id = 1L;
        // Configuración del mock: cuando se llame a findById con el ID 1L, devuelve un Optional con producto1
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto1));

        // Ejecución
        Optional<Producto> productoEncontrado = productoService.buscarPorId(id);

        // Verificación
        assertTrue(productoEncontrado.isPresent());
        // CORRECCIÓN: Usar getIdProducto() en lugar de getId()
        assertEquals(id, productoEncontrado.get().getIdProducto());
        assertEquals("Zapatilla Deportiva", productoEncontrado.get().getNombre());

        // Verifica que el método findById del repositorio haya sido llamado
        verify(productoRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 99L;
        // Configuración del mock: cuando se llame a findById con el ID 99L, devuelve un Optional vacío
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        Optional<Producto> productoEncontrado = productoService.buscarPorId(id);

        // Verificación
        assertFalse(productoEncontrado.isPresent());

        // Verifica que el método findById del repositorio haya sido llamado
        verify(productoRepository, times(1)).findById(id);
    }

    @Test
    void testEliminarProducto() {
        Long id = 1L;
        // La eliminación no devuelve nada, solo verificamos que se llame al método
        // No necesitamos configurar el mock, ya que deleteById devuelve void por defecto

        // Ejecución
        productoService.eliminar(id);

        // Verificación: verifica que el método deleteById del repositorio haya sido llamado
        // y que no se hayan lanzado excepciones
        verify(productoRepository, times(1)).deleteById(id);
    }
}