package br.com.allan.medStar.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank(message = "{cep.obrigatorio}")
        @Pattern(regexp = "\\d{8}", message = "{cep.invalido}")
        String cep,

        @NotBlank(message = "{uf.obrigatorio}")
        String uf,

        @NotBlank(message = "{cidade.obrigatoria}")
        String cidade,

        @NotBlank(message = "{bairro.obrigatorio}")
        String bairro,

        @NotBlank(message = "{logradouro.obrigatorio}")
        String logradouro,

        String numero,
        String complemento) {
}