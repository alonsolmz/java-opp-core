package pe.edu.utp.AVANCE02PROYECTO_GRUPO2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Cliente;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ClienteRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl.ClienteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase ClienteServiceImpl.
 * Utiliza Mockito para simular la capa de persistencia (ClienteRepository).
 */
@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    // Simula la dependencia del repositorio
    @Mock
    private ClienteRepository clienteRepository;

    // Inyecta los mocks en la clase a probar
    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        // Inicializa objetos Cliente de ejemplo (asumiendo que el ID es 'idCliente')
        cliente1 = new Cliente();
        cliente1.setIdCliente(1L);
        cliente1.setNombre("Ana Lopez");
        cliente1.setDni("87654321");
        cliente1.setEmail("ana.lopez@mail.com");

        cliente2 = new Cliente();
        cliente2.setIdCliente(2L);
        cliente2.setNombre("Pedro Garcia");
        cliente2.setDni("12345678");
        cliente2.setEmail("pedro.garcia@mail.com");
    }

    @Test
    void testGuardarCliente() {
        // Configuración del mock
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        // Ejecución
        Cliente clienteGuardado = clienteService.guardar(cliente1);

        // Verificación
        assertNotNull(clienteGuardado);
        assertEquals("Ana Lopez", clienteGuardado.getNombre());
        verify(clienteRepository, times(1)).save(cliente1);
    }

    @Test
    void testListarTodos() {
        // Configuración del mock
        List<Cliente> listaEsperada = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findAll()).thenReturn(listaEsperada);

        // Ejecución
        List<Cliente> clientes = clienteService.listarTodos();

        // Verificación
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Pedro Garcia", clientes.get(1).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdEncontrado() {
        Long id = 1L;
        // Configuración del mock
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente1));

        // Ejecución
        Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(id);

        // Verificación
        assertTrue(clienteEncontrado.isPresent());
        assertEquals(id, clienteEncontrado.get().getIdCliente());
        assertEquals("87654321", clienteEncontrado.get().getDni());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 99L;
        // Configuración del mock
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(id);

        // Verificación
        assertFalse(clienteEncontrado.isPresent());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void testEliminarCliente() {
        Long id = 1L;

        // Ejecución
        clienteService.eliminar(id);

        // Verificación
        verify(clienteRepository, times(1)).deleteById(id);
    }
}
