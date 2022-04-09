package com.cenfotec.examen2.repository;

import com.cenfotec.examen2.domain.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
}
