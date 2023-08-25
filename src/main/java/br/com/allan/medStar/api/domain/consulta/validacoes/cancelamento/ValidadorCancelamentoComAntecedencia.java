package br.com.allan.medStar.api.domain.consulta.validacoes.cancelamento;

import br.com.allan.medStar.api.domain.consulta.ConsultaRepository;
import br.com.allan.medStar.api.domain.consulta.DadosCancelamentoConsulta;
import br.com.allan.medStar.api.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoComAntecedencia implements ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h");
        }
    }
}
