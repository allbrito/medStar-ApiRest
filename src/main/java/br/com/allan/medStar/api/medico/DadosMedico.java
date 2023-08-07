package br.com.allan.medStar.api.medico;

import br.com.allan.medStar.api.endereco.DadosEndereco;

public record DadosMedico(String nome, String email,
                          String telefone, String crm,
                          Especialidade especialidade, DadosEndereco dadosEndereco) {
}