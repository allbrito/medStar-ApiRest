package br.com.allan.medStar.api.paciente;

import br.com.allan.medStar.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

        String email,
        String telefone,
        DadosEndereco endereco) {
}
