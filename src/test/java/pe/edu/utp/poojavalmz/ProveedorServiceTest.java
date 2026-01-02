package pe.edu.utp.poojavalmz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import pe.edu.utp.poojavalmz.exception.ResourceNotFoundException;
import pe.edu.utp.poojavalmz.model.Proveedor;
import pe.edu.utp.poojavalmz.repository.ProveedorRepository;
import pe.edu.utp.poojavalmz.service.IProveedorService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private IProveedorService proveedorService;

    private Proveedor proveedor1;
    private Proveedor proveedor2;

    @BeforeEach
    void setUp() {

        proveedor1 = new Proveedor();
        proveedor1.setIdProveedor(1L);

        proveedor1.setNombre("Shoes S.A.C.");
        proveedor1.setRuc("20123456789");

        proveedor2 = new Proveedor();
        proveedor2.setIdProveedor(2L);

        proveedor2.setNombre("Textil Perú E.I.R.L.");
        proveedor2.setRuc("10987654321");
    }

    // Pruebas para GUARDAR

    @Test
    void testGuardarProveedorExitoso() {

        when(proveedorRepository.save(proveedor1)).thenReturn(proveedor1);

        Proveedor proveedorGuardado = proveedorService.guardar(proveedor1);

        assertNotNull(proveedorGuardado);
        assertEquals("Shoes S.A.C.", proveedorGuardado.getNombre());
        verify(proveedorRepository, times(1)).save(proveedor1);
    }

    @Test
    void testGuardarProveedorConFallaDeIntegridad() {

        when(proveedorRepository.save(proveedor1)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> {
            proveedorService.guardar(proveedor1);
        });
        verify(proveedorRepository, times(1)).save(proveedor1);
    }


    @Test
    void testListarTodos() {

        List<Proveedor> listaEsperada = Arrays.asList(proveedor1, proveedor2);
        when(proveedorRepository.findAll()).thenReturn(listaEsperada);


        List<Proveedor> proveedores = proveedorService.listarTodos();


        assertNotNull(proveedores);
        assertEquals(2, proveedores.size());
        assertEquals("Textil Perú E.I.R.L.", proveedores.get(1).getNombre());
        verify(proveedorRepository, times(1)).findAll();
    }



    @Test
    void testBuscarPorIdEncontrado() {
        Long id = 1L;

        when(proveedorRepository.findById(id)).thenReturn(Optional.of(proveedor1));


        Proveedor proveedorEncontrado = proveedorService.buscarPorId(id).get();


        assertNotNull(proveedorEncontrado);
        assertEquals(id, proveedorEncontrado.getIdProveedor());
        assertEquals("20123456789", proveedorEncontrado.getRuc());
        verify(proveedorRepository, times(1)).findById(id);
    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        Long id = 99L;

        when(proveedorRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> {
            proveedorService.buscarPorId(id);
        });
        verify(proveedorRepository, times(1)).findById(id);
    }


    @Test
    void testEliminarProveedorExitoso() {
        Long id = 1L;

        when(proveedorRepository.existsById(id)).thenReturn(true);



        proveedorService.eliminar(id);

        verify(proveedorRepository, times(1)).existsById(id);
        verify(proveedorRepository, times(1)).deleteById(id);
    }

    @Test
    void testEliminarProveedorNoEncontradoPorExistencia() {
        Long id = 99L;

        when(proveedorRepository.existsById(id)).thenReturn(false);


        assertThrows(ResourceNotFoundException.class, () -> {
            proveedorService.eliminar(id);
        });
        verify(proveedorRepository, times(1)).existsById(id);

        verify(proveedorRepository, times(0)).deleteById(id);
    }

    @Test
    void testEliminarProveedorConExcepcionDeBDEliminacion() {
        Long id = 1L;

        when(proveedorRepository.existsById(id)).thenReturn(true);
        doThrow(EmptyResultDataAccessException.class).when(proveedorRepository).deleteById(id);


        assertThrows(ResourceNotFoundException.class, () -> {
            proveedorService.eliminar(id);
        });
        verify(proveedorRepository, times(1)).existsById(id);
        verify(proveedorRepository, times(1)).deleteById(id);
    }
}