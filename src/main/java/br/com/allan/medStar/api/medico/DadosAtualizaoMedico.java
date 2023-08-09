package br.com.allan.medStar.api.medico;

import br.com.allan.medStar.api.endereco.DadosEndereco;

public record DadosAtualizaoMedico(

        String email,
        String telefone,
        DadosEndereco endereco) {
}
