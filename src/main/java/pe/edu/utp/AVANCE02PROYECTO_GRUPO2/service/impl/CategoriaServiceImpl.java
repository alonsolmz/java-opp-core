package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Categoria;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.CategoriaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional
    @Override
    public Categoria guardar(Categoria categoria) {
        // Lógica de negocio si es necesaria (ej. validaciones)
        return categoriaRepository.save(categoria);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }


    @Transactional
    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
