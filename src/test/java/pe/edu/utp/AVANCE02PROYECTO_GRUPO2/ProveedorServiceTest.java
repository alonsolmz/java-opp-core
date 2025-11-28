package pe.edu.utp.AVANCE02PROYECTO_GRUPO2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ProveedorRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProveedorService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase ProveedorService.
 * Valida la lógica de negocio y el manejo de excepciones.
 */
@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    // Simula la dependencia del repositorio
    @Mock
    private ProveedorRepository proveedorRepository;

    // Inyecta los mocks en la clase a probar
    @InjectMocks
    private IProveedorService proveedorService;

    private Proveedor proveedor1;
    private Proveedor proveedor2;

    @BeforeEach
    void setUp() {
        // Inicializa objetos Proveedor de ejemplo (asumiendo que el ID es 'idProveedor')
        proveedor1 = new Proveedor();
        proveedor1.setIdProveedor(1L);
        // CORRECCIÓN: Usar setNombre() para el campo nombre/razón social
        proveedor1.setNombre("Shoes S.A.C.");
        proveedor1.setRuc("20123456789");

        proveedor2 = new Proveedor();
        proveedor2.setIdProveedor(2L);
        // CORRECCIÓN: Usar setNombre() para el campo nombre/razón social
        proveedor2.setNombre("Textil Perú E.I.R.L.");
        proveedor2.setRuc("10987654321");
    }

    // --- Pruebas para GUARDAR (Crear/Actualizar) ---

    @Test
    void testGuardarProveedorExitoso() {
        // Configuración del mock: cuando se llame a save, devuelve el proveedor
        when(proveedorRepository.save(proveedor1)).thenReturn(proveedor1);

        // Ejecución
        Proveedor proveedorGuardado = proveedorService.guardar(proveedor1);

        // Verificación
        assertNotNull(proveedorGuardado);
        assertEquals("Shoes S.A.C.", proveedorGuardado.getNombre());
        verify(proveedorRepository, times(1)).save(proveedor1);
    }

    @Test
    void testGuardarProveedorConFallaDeIntegridad() {
        // Configuración del mock: simula una excepción de base de datos (ej. RUC duplicado)
        when(proveedorRepository.save(proveedor1)).thenThrow(DataIntegrityViolationException.class);

        // Verificación: Esperamos que se lance la excepción DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> {
            proveedorService.guardar(proveedor1);
        });
        verify(proveedorRepository, times(1)).save(proveedor1);
    }

    // --- Pruebas para LISTAR ---

    @Test
    void testListarTodos() {
        // Configuración del mock
        List<Proveedor> listaEsperada = Arrays.asList(proveedor1, proveedor2);
        when(proveedorRepository.findAll()).thenReturn(listaEsperada);

        // Ejecución
        List<Proveedor> proveedores = proveedorService.listarTodos();

        // Verificación
        assertNotNull(proveedores);
        assertEquals(2, proveedores.size());
        assertEquals("Textil Perú E.I.R.L.", proveedores.get(1).getNombre());
        verify(proveedorRepository, times(1)).findAll();
    }

    // --- Pruebas para BUSCAR POR ID ---

    @Test
    void testBuscarPorIdEncontrado() {
        Long id = 1L;
        // Configuración del mock
        when(proveedorRepository.findById(id)).thenReturn(Optional.of(proveedor1));

        // Ejecución
        // CORRECCIÓN CONFIRMADA: Aseguramos que se llama con el parámetro 'id'
        Proveedor proveedorEncontrado = proveedorService.buscarPorId(id);

        // Verificación
        assertNotNull(proveedorEncontrado);
        assertEquals(id, proveedorEncontrado.getIdProveedor());
        assertEquals("20123456789", proveedorEncontrado.getRuc());
        verify(proveedorRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 99L;
        // Configuración del mock
        when(proveedorRepository.findById(id)).thenReturn(Optional.empty());

        // Verificación: Esperamos que se lance ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            proveedorService.buscarPorId(id);
        });
        verify(proveedorRepository, times(1)).findById(id);
    }

    // --- Pruebas para ELIMINAR ---

    @Test
    void testEliminarProveedorExitoso() {
        Long id = 1L;
        // Configuración del mock: el proveedor existe y se puede eliminar
        when(proveedorRepository.existsById(id)).thenReturn(true);
        // deleteById devuelve void, no necesitamos when()

        // Ejecución
        // CORRECCIÓN CONFIRMADA: Aseguramos que se llama con el parámetro 'id'
        proveedorService.eliminar(id);

        // Verificación: Se verifica que se consultó si existe y luego se llamó a deleteById
        verify(proveedorRepository, times(1)).existsById(id);
        verify(proveedorRepository, times(1)).deleteById(id);
    }

    @Test
    void testEliminarProveedorNoEncontradoPorExistencia() {
        Long id = 99L;
        // Configuración del mock: el proveedor NO existe
        when(proveedorRepository.existsById(id)).thenReturn(false);

        // Verificación: Esperamos que se lance ResourceNotFoundException antes de intentar eliminar
        assertThrows(ResourceNotFoundException.class, () -> {
            proveedorService.eliminar(id);
        });
        verify(proveedorRepository, times(1)).existsById(id);
        // Verificamos que deleteById NUNCA se llama
        verify(proveedorRepository, times(0)).deleteById(id);
    }

    @Test
    void testEliminarProveedorConExcepcionDeBDEliminacion() {
        Long id = 1L;
        // Configuración del mock: el existeById pasa, pero deleteById lanza una excepción (simulando que no estaba)
        when(proveedorRepository.existsById(id)).thenReturn(true);
        doThrow(EmptyResultDataAccessException.class).when(proveedorRepository).deleteById(id);

        // Verificación: Esperamos que se relance ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            proveedorService.eliminar(id);
        });
        verify(proveedorRepository, times(1)).existsById(id);
        verify(proveedorRepository, times(1)).deleteById(id);
    }
}