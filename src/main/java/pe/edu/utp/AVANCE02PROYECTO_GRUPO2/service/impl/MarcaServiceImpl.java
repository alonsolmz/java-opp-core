package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Marca;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.MarcaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz IMarcaService.
 */
@Service
public class MarcaServiceImpl implements IMarcaService {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    /**
     * Guarda una nueva marca o actualiza una existente.
     * @param marca El objeto Marca a guardar.
     * @return La marca guardada.
     */
    @Transactional
    @Override
    public Marca guardar(Marca marca) {
        // Lógica de negocio: se podría añadir validación para que el nombre no esté duplicado
        // usando marcaRepository.findByNombre(marca.getNombre());
        return marcaRepository.save(marca);
    }

    /**
     * Lista todas las marcas.
     * @return Una lista de todas las marcas.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Marca> listarTodos() {
        return marcaRepository.findAll();
    }

    /**
     * Busca una marca por su identificador único.
     * @param id El ID de la marca.
     * @return Un Optional que contiene la marca si existe.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Marca> buscarPorId(Long id) {
        return marcaRepository.findById(id);
    }

    /**
     * Elimina una marca por su ID.
     * @param id El ID de la marca a eliminar.
     */
    @Transactional
    @Override
    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }
}
