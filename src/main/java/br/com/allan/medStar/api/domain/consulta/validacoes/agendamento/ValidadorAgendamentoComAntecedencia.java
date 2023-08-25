package br.com.allan.medStar.api.domain.consulta.validacoes.agendamento;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAgendamentoComAntecedencia implements ValidadorAgendamentoConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmDias = Duration.between(agora, dataConsulta).toDays();

        if (diferencaEmDias < 3) {
            throw new ValidacaoException("O agendamento para consulta deve ser feito com no mínimo 3 dias de antecedência");
        }
    }
}
