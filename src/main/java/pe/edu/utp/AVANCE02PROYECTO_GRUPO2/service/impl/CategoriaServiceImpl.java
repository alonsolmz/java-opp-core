package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Categoria;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.CategoriaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ICategoriaService.
 * Contiene la lógica de negocio para la gestión de Categorías.
 */
@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Guarda una nueva categoría o actualiza una existente.
     * @param categoria El objeto Categoria a guardar.
     * @return La categoría guardada.
     */
    @Transactional
    @Override
    public Categoria guardar(Categoria categoria) {
        // Lógica de negocio si es necesaria (ej. validaciones)
        return categoriaRepository.save(categoria);
    }

    /**
     * Lista todas las categorías.
     * @return Una lista de todas las categorías.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    /**
     * Busca una categoría por su identificador único.
     * @param id El ID de la categoría.
     * @return Un Optional que contiene la categoría si existe.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Elimina una categoría por su ID.
     * @param id El ID de la categoría a eliminar.
     */
    @Transactional
    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
