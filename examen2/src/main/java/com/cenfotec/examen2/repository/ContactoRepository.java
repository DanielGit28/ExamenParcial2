package com.cenfotec.examen2.repository;

import com.cenfotec.examen2.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    List<Contacto> getContactosByIdCliente(Long id);
}
