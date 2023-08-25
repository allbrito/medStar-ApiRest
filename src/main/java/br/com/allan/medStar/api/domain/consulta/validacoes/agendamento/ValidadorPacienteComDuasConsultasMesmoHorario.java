package br.com.allan.medStar.api.domain.consulta.validacoes.agendamento;

import br.com.allan.medStar.api.domain.consulta.ConsultaRepository;
import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteComDuasConsultasMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteConsulta = repository.existsByPacienteIdAndData(dados.idPaciente(), dados.data());
        if (pacienteConsulta) {
            throw new ValidacaoException("O paciente já possui uma consulta nesse mesmo horário");
        }
    }
}
