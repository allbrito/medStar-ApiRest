package br.com.allan.medStar.api.domain.medico;

import br.com.allan.medStar.api.domain.endereco.DadosEndereco;

public record DadosAtualizaoMedico(

        String email,
        String telefone,
        DadosEndereco endereco) {
}
