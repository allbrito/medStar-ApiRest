package br.com.allan.medStar.api.domain.paciente;

import br.com.allan.medStar.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

        String email,
        String telefone,
        DadosEndereco endereco) {
}
