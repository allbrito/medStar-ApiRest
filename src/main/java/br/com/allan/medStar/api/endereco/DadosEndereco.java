package br.com.allan.medStar.api.endereco;

public record DadosEndereco(String cep, String uf,
                            String cidade, String bairro,
                            String logradouro, String numero,
                            String complemento) {
}