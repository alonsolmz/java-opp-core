package pe.edu.utp.poojavalmz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.poojavalmz.model.Cliente;
import pe.edu.utp.poojavalmz.repository.ClienteRepository;
import pe.edu.utp.poojavalmz.service.impl.ClienteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;


    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {

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

        when(clienteRepository.save(cliente1)).thenReturn(cliente1);


        Cliente clienteGuardado = clienteService.guardar(cliente1);


        assertNotNull(clienteGuardado);
        assertEquals("Ana Lopez", clienteGuardado.getNombre());
        verify(clienteRepository, times(1)).save(cliente1);
    }

    @Test
    void testListarTodos() {

        List<Cliente> listaEsperada = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findAll()).thenReturn(listaEsperada);


        List<Cliente> clientes = clienteService.listarTodos();


        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Pedro Garcia", clientes.get(1).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdEncontrado() {
        Long id = 1L;

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente1));


        Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(id);


        assertTrue(clienteEncontrado.isPresent());
        assertEquals(id, clienteEncontrado.get().getIdCliente());
        assertEquals("87654321", clienteEncontrado.get().getDni());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 99L;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());


        Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(id);


        assertFalse(clienteEncontrado.isPresent());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void testEliminarCliente() {
        Long id = 1L;


        clienteService.eliminar(id);


        verify(clienteRepository, times(1)).deleteById(id);
    }
}
