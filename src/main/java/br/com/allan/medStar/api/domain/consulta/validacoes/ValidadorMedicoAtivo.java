package br.com.allan.medStar.api.domain.consulta.validacoes;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import br.com.allan.medStar.api.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    private MedicoRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }
        var ativo = repository.findAtivoById(dados.idMedico());
        if (!ativo) {
            throw new ValidacaoException("O médico solicitado está inativo");
        }
    }
}
