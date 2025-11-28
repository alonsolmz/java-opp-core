package pe.edu.utp.AVANCE02PROYECTO_GRUPO2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.EmpleadoRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl.EmpleadoServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase EmpleadoServiceImpl.
 * Utiliza Mockito para simular la capa de persistencia (EmpleadoRepository).
 * NOTA: Se asume que la entidad Empleado o Persona tiene un campo 'puesto' para el cargo.
 */
@ExtendWith(MockitoExtension.class)
class EmpleadoServiceImplTest {

    // Simula la dependencia del repositorio
    @Mock
    private EmpleadoRepository empleadoRepository;

    // Inyecta los mocks en la clase a probar
    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    void setUp() {
        // Inicializa objetos Empleado de ejemplo (asumiendo que el ID es 'idEmpleado' y el cargo es 'puesto')
        empleado1 = new Empleado();
        empleado1.setIdEmpleado(1L);
        empleado1.setNombre("Javier");
        empleado1.setApellido("Soto");
        empleado1.setDni("12345678");
        // Se asume 'puesto' como el campo para el cargo
        empleado1.setPuesto("Vendedor");
        empleado1.setEmail("javier.soto@utp.pe");
        empleado1.setTelefono("987654321");

        empleado2 = new Empleado();
        empleado2.setIdEmpleado(2L);
        empleado2.setNombre("Carla");
        empleado2.setApellido("Rojas");
        empleado2.setDni("87654321");
        // Se asume 'puesto' como el campo para el cargo
        empleado2.setPuesto("Cajero");
        empleado2.setEmail("carla.rojas@utp.pe");
        empleado2.setTelefono("912345678");
    }

    @Test
    void testGuardarEmpleado() {
        // Configuración del mock
        when(empleadoRepository.save(empleado1)).thenReturn(empleado1);

        // Ejecución
        Empleado empleadoGuardado = empleadoService.guardar(empleado1);

        // Verificación
        assertNotNull(empleadoGuardado);
        assertEquals("Javier", empleadoGuardado.getNombre());
        assertEquals("Vendedor", empleadoGuardado.getPuesto());

        verify(empleadoRepository, times(1)).save(empleado1);
    }

    @Test
    void testListarTodos() {
        // Configuración del mock
        List<Empleado> listaEsperada = Arrays.asList(empleado1, empleado2);
        when(empleadoRepository.findAll()).thenReturn(listaEsperada);

        // Ejecución
        List<Empleado> empleados = empleadoService.listarTodos();

        // Verificación
        assertNotNull(empleados);
        assertEquals(2, empleados.size());
        assertEquals("Cajero", empleados.get(1).getPuesto());

        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdEncontrado() {
        Long id = 1L;
        // Configuración del mock
        when(empleadoRepository.findById(id)).thenReturn(Optional.of(empleado1));

        // Ejecución
        Optional<Empleado> empleadoEncontrado = empleadoService.buscarPorId(id);

        // Verificación
        assertTrue(empleadoEncontrado.isPresent());
        assertEquals(id, empleadoEncontrado.get().getIdEmpleado());
        assertEquals("Vendedor", empleadoEncontrado.get().getPuesto());

        verify(empleadoRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 99L;
        // Configuración del mock
        when(empleadoRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        Optional<Empleado> empleadoEncontrado = empleadoService.buscarPorId(id);

        // Verificación
        assertFalse(empleadoEncontrado.isPresent());

        verify(empleadoRepository, times(1)).findById(id);
    }

    @Test
    void testEliminarEmpleado() {
        Long id = 1L;
        // La eliminación no devuelve nada, solo verificamos que se llame al método

        // Ejecución
        empleadoService.eliminar(id);

        // Verificación
        verify(empleadoRepository, times(1)).deleteById(id);
    }
}