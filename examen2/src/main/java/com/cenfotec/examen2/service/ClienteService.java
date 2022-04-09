package com.cenfotec.examen2.service;

import com.cenfotec.examen2.domain.Auditor;
import com.cenfotec.examen2.domain.Cliente;
import com.cenfotec.examen2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public void saveCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.getById(id);
    }
    public Cliente updateCliente(Cliente dto) {
        Cliente cliente = clienteRepository.getById(dto.getId());

        cliente.setId(dto.getId());
        cliente.setRazonSocial(dto.getRazonSocial());
        cliente.setCedulaJuridica(dto.getCedulaJuridica());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());


        return clienteRepository.save(cliente);
    }
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
