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

    public Endereco(DadosEndereco dados) {
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.bairro = dados.bairro();
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if(dados.cep() != cep) this.cep = dados.cep();

        if(dados.uf() != uf) this.uf = dados.uf();

        if(dados.cidade() != cidade) this.cidade = dados.cidade();

        if(dados.bairro() != bairro) this.bairro = dados.bairro();

        if(dados.logradouro() != logradouro) this.logradouro = dados.logradouro();

        if(dados.numero() != numero) this.numero = dados.numero();

        if(dados.complemento() != complemento) this.complemento = dados.complemento();
    }
}