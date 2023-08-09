package br.com.allan.medStar.api.paciente;

import br.com.allan.medStar.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Table(name = "pacientes")
@Entity(name="Paciente")
public class PacienteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public PacienteEntity(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if(dados.email() != null) this.nome = dados.email();

        if(dados.telefone() != null) this.telefone = dados.telefone();

        if(dados.endereco() != null) this.endereco.atualizarInformacoes(dados.endereco());
    }
}
