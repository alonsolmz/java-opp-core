package pe.edu.utp.poojavalmz.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.utp.poojavalmz.model.Cliente;
import pe.edu.utp.poojavalmz.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.poojavalmz.service.IClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


        @Transactional
        @Override
        public Cliente guardar (Cliente cliente){
            // Lógica de negocio: se podría añadir validación para DNI único o formato.
            return clienteRepository.save(cliente);
        }


        @Transactional(readOnly = true)
        @Override
        public List<Cliente> listarTodos () {
            return clienteRepository.findAll();
        }


        @Transactional(readOnly = true)
        @Override
        public Optional<Cliente> buscarPorId (Long id){
            return clienteRepository.findById(id);
        }


        @Transactional
        @Override
        public void eliminar (Long id){
            clienteRepository.deleteById(id);
        }
    }
