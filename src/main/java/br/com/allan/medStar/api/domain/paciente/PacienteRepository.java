package br.com.allan.medStar.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    List<PacienteEntity> findAllByAtivoTrue();
    Page<PacienteEntity> findAllByAtivoTrue(Pageable paginacao);
}
