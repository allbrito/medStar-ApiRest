package br.com.allan.medStar.api.domain.consulta.validacoes;

import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import br.com.allan.medStar.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var ativo = repository.findAtivoById(dados.idPaciente());
        if (!ativo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
