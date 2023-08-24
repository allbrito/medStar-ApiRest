package br.com.allan.medStar.api.domain.consulta.validacoes;

import br.com.allan.medStar.api.domain.consulta.ConsultaRepository;
import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;

public class ValidadorMedicoComOutraConsultaMesmoHorario implements ValidadorAgendamentoConsulta {

    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoConsulta = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoConsulta) {
            throw new ValidacaoException("Medico já possui uma consulta nesse horário");
        }
    }
}
