package com.cenfotec.examen2.service;

import com.cenfotec.examen2.domain.Contacto;
import com.cenfotec.examen2.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService {
    @Autowired
    ContactoRepository contactoRepository;


    public void saveContacto(Contacto persona){
        contactoRepository.save(persona);
    }

    public List<Contacto> getAll(){
        return contactoRepository.findAll();
    }
    public List<Contacto> getContactosByIdCliente(Long idCliente){
        return contactoRepository.getContactosByIdCliente(idCliente);
    }
    public Contacto getById(Long id) {
        return contactoRepository.getById(id);
    }
    public Contacto updateContacto(Contacto dto) {
        Contacto contacto = contactoRepository.getById(dto.getId());

        contacto.setId(dto.getId());
        contacto.setNombre(dto.getNombre());
        contacto.setApellido1(dto.getApellido1());
        contacto.setApellido2(dto.getApellido2());
        contacto.setCorreo(dto.getCorreo());
        contacto.setTelefono(dto.getTelefono());
        contacto.setIdCliente(dto.getIdCliente());


        return contactoRepository.save(contacto);
    }
    public void deletePersona(Long id) {
        contactoRepository.deleteById(id);
    }
}
