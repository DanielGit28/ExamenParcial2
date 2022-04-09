package com.cenfotec.examen2.repository;

import com.cenfotec.examen2.domain.Auditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Long> {
}
