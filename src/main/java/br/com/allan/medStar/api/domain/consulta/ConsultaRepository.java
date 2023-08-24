package br.com.allan.medStar.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
    boolean existsByPacienteIdAndData(Long idPaciente, LocalDateTime data);
}
