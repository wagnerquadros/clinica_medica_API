package com.wagnerquadros.clinicamedica.repository;

import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
