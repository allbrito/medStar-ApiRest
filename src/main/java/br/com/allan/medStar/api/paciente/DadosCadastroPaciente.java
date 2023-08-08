package br.com.allan.medStar.api.paciente;

import br.com.allan.medStar.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco) {
}
