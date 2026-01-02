package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Empleado;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.EmpleadoRepository;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    @Transactional
    @Override
    public Empleado guardar(Empleado empleado) {

        return empleadoRepository.save(empleado);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }


    @Transactional
    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
