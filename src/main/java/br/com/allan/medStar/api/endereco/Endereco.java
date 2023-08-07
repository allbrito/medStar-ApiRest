package br.com.allan.medStar.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
}