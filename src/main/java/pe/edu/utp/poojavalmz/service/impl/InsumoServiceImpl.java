package pe.edu.utp.poojavalmz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.poojavalmz.model.Insumo;
import pe.edu.utp.poojavalmz.repository.InsumoRepository;
import pe.edu.utp.poojavalmz.repository.ProveedorRepository;
import pe.edu.utp.poojavalmz.service.IInsumoService;

import java.util.List;
import java.util.Optional;


@Service
public class InsumoServiceImpl implements IInsumoService {


    @Autowired
    private InsumoRepository insumoRepository;


    @Autowired
    private ProveedorRepository proveedorRepository;


    @Override
    public Insumo guardar(Insumo insumo) {
        // Lógica de negocio antes de guardar (ej. validación de stock inicial)
        if (insumo.getStockActual() == null || insumo.getStockActual() < 0) {
            insumo.setStockActual(0);
        }
        return insumoRepository.save(insumo);
    }

    @Override
    public Optional<Insumo> buscarPorId(Long id) {
        return insumoRepository.findById(id);
    }

    @Override
    public List<Insumo> listarTodos() {
        return insumoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        insumoRepository.deleteById(id);
    }



    @Override
    public List<Insumo> buscarPorProveedor(Long idProveedor) {

        return insumoRepository.findByProveedorIdProveedor(idProveedor);
    }
}
