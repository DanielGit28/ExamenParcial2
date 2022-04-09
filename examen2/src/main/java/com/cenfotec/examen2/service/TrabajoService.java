package com.cenfotec.examen2.service;

import com.cenfotec.examen2.domain.Trabajo;
import com.cenfotec.examen2.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajoService {
    @Autowired
    TrabajoRepository trabajoRepository;

    public void saveTrabajo(Trabajo trabajo){
        trabajoRepository.save(trabajo);
    }

    public List<Trabajo> getAll(){
        return trabajoRepository.findAll();
    }

    public Trabajo getById(Long id) {
        return trabajoRepository.getById(id);
    }
    public Trabajo updateTrabajo(Trabajo dto) {
        Trabajo trabajo = trabajoRepository.getById(dto.getId());

        trabajo.setId(dto.getId());
        trabajo.setAuditoria(dto.getAuditoria());
        trabajo.setFecha(dto.getFecha());
        trabajo.setIdCliente(dto.getIdCliente());

        return trabajoRepository.save(trabajo);
    }
    public void deleteTrabajo(Long id) {
        trabajoRepository.deleteById(id);
    }
}
