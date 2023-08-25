package br.com.allan.medStar.api.domain.consulta.validacoes.agendamento;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
