package br.com.allan.medStar.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    List<MedicoEntity> findAllByAtivoTrue();
    Page<MedicoEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""            
            select m from Medico m where
            m.ativo = true and
            m.especialidade = :especialidade and
            m.id not in(
                select c.medico.id from Consulta c where
                c.data = :data
            )
            order by rand() limit 1
            """)
    MedicoEntity medicoLivreData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m where 
            m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);
}