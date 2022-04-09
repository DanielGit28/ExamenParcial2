package com.cenfotec.examen2.service;

import com.cenfotec.examen2.domain.Auditor;
import com.cenfotec.examen2.repository.AuditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditorService {
    @Autowired
    AuditorRepository auditorRepository;

    public void saveAuditor(Auditor auditor){
        auditorRepository.save(auditor);
    }

    public List<Auditor> getAll(){
        return auditorRepository.findAll();
    }

    public Auditor getById(Long id) {
        return auditorRepository.getById(id);
    }
    public Auditor updateAuditor(Auditor dto) {
        Auditor auditor = auditorRepository.getById(dto.getId());

        auditor.setId(dto.getId());
        auditor.setNombre(dto.getNombre());
        auditor.setApellido1(dto.getApellido1());
        auditor.setApellido2(dto.getApellido2());
        auditor.setDireccion(dto.getDireccion());
        auditor.setTelefono(dto.getTelefono());
        System.out.println(dto.getFechaNacimiento());
        auditor.setFechaNacimiento(dto.getFechaNacimiento());
        auditor.setCorreo(dto.getCorreo());
        auditor.setDisponibilidadViaje(dto.getDisponibilidadViaje());
        auditor.setEspecialidad(dto.getEspecialidad());
        auditor.setEstado(dto.getEstado());


        return auditorRepository.save(auditor);
    }
    public void deleteAuditor(Long id) {
        auditorRepository.deleteById(id);
    }

}
