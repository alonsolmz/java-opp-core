package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Cliente;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IClienteService;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.service.IMarcaService;

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

        /**
         * Lista todos los clientes.
         * @return Una lista de todos los clientes.
         */
        @Transactional(readOnly = true)
        @Override
        public List<Cliente> listarTodos () {
            return clienteRepository.findAll();
        }

        /**
         * Busca un cliente por su identificador único.
         * @param id El ID del cliente.
         * @return Un Optional que contiene el cliente si existe.
         */
        @Transactional(readOnly = true)
        @Override
        public Optional<Cliente> buscarPorId (Long id){
            return clienteRepository.findById(id);
        }

        /**
         * Elimina un cliente por su ID.
         * @param id El ID del cliente a eliminar.
         */
        @Transactional
        @Override
        public void eliminar (Long id){
            clienteRepository.deleteById(id);
        }
    }
