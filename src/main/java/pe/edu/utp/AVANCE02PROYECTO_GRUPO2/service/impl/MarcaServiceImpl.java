package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Marca;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.MarcaRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class MarcaServiceImpl implements IMarcaService {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }


    @Transactional
    @Override
    public Marca guardar(Marca marca) {

        return marcaRepository.save(marca);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Marca> listarTodos() {
        return marcaRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Marca> buscarPorId(Long id) {
        return marcaRepository.findById(id);
    }


    @Transactional
    @Override
    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }
}
