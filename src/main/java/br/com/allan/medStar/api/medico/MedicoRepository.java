package br.com.allan.medStar.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    List<MedicoEntity> findAllByAtivoTrue();
    Page<MedicoEntity> findAllByAtivoTrue(Pageable paginacao);
}