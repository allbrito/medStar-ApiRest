package br.com.allan.medStar.api.domain.consulta.validacoes;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var horarioSemExpediente = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if (horarioSemExpediente || domingo) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}