package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ProveedorRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación de la interfaz IProveedorService.
 * Contiene la lógica de negocio para la gestión de Proveedores.
 */
@Service
public class ProveedorServiceImpl implements IProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    /**
     * Guarda un nuevo proveedor o actualiza uno existente.
     * @param proveedor El objeto Proveedor a guardar.
     * @return El proveedor guardado.
     */
    @Transactional // Para operaciones de ESCRITURA (INSERT/UPDATE)
    @Override
    public Proveedor guardar(Proveedor proveedor) {
        // Lógica de negocio: Ej. Validación de RUC
        if (proveedor.getRuc() == null || proveedor.getRuc().length() != 11) {
            throw new IllegalArgumentException("El RUC debe tener 11 dígitos.");
        }
        return proveedorRepository.save(proveedor);
    }

    /**
     * Lista todos los proveedores en la base de datos.
     * @return Una lista de todos los proveedores.
     */
    @Transactional(readOnly = true) // Para operaciones de LECTURA
    @Override
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    /**
     * Busca un proveedor por su ID.
     *
     * @param id El ID del proveedor.
     * @return Un Optional que contiene el proveedor si existe.
     */
    @Transactional(readOnly = true) // Para operaciones de LECTURA
    @Override
    public Proveedor buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    /**
     * Elimina un proveedor por su ID.
     * @param id El ID del proveedor a eliminar.
     */
    @Transactional // Para operaciones de ESCRITURA (DELETE)
    @Override
    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
}
