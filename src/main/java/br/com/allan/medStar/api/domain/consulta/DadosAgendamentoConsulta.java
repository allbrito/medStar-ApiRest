package br.com.allan.medStar.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long idMedico,
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data
) {
}
