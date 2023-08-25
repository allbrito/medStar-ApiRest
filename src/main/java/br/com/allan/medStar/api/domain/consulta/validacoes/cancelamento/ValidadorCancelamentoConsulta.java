package br.com.allan.medStar.api.domain.consulta.validacoes.cancelamento;

import br.com.allan.medStar.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {

    void validar(DadosCancelamentoConsulta dados);
}
