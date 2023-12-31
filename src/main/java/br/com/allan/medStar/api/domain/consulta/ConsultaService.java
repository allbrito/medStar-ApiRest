package br.com.allan.medStar.api.domain.consulta;

import br.com.allan.medStar.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import br.com.allan.medStar.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import br.com.allan.medStar.api.domain.medico.MedicoEntity;
import br.com.allan.medStar.api.domain.medico.MedicoRepository;
import br.com.allan.medStar.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa hora");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new ConsultaEntity(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private MedicoEntity escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for especificado!");
        }

        return medicoRepository.medicoAleatorioLivreData(dados.especialidade(), dados.data());
    }
}
