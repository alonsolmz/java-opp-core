package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception.ResourceNotFoundException;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Proveedor;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ProveedorRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IProveedorService;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements IProveedorService {


    private final ProveedorRepository proveedorRepository;


    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }


    @Transactional
    @Override
    public Proveedor guardar(Proveedor proveedor) {
        if (proveedor.getRuc() == null || proveedor.getRuc().length() != 11) {
            throw new IllegalArgumentException("El RUC debe tener 11 dígitos.");
        }
        return proveedorRepository.save(proveedor);
    }


    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }


    @Override
    public Optional<Proveedor> buscarPorId(Long id) {

        return proveedorRepository.findById(id);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {

        proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));

        proveedorRepository.deleteById(id);
    }
}