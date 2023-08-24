package br.com.allan.medStar.api.domain.consulta.validacoes;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import br.com.allan.medStar.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
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
