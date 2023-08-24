package br.com.allan.medStar.api.domain.consulta.validacoes;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
