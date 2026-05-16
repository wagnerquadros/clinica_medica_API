package com.wagnerquadros.clinicamedica.repository;

import com.wagnerquadros.clinicamedica.entity.consulta.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long medicoId, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(@NotNull Long pacienteId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
