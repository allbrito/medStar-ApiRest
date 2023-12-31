package br.com.allan.medStar.api.domain.medico;

import br.com.allan.medStar.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Table(name="medicos")
@Entity(name="Medico")
public class MedicoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public MedicoEntity(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizaoMedico dados) {
        if(dados.email() != null) this.telefone = dados.email();

        if(dados.telefone() != null) this.telefone = dados.telefone();

        if(dados.endereco() != null) this.endereco.atualizarInformacoes(dados.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }
}