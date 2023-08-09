package br.com.allan.medStar.api.paciente;

import br.com.allan.medStar.api.endereco.Endereco;

public record DadosCompletosPaciente(

        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        Endereco endereco) {

    public DadosCompletosPaciente(PacienteEntity pacienteEntity) {
        this(pacienteEntity.getId(), pacienteEntity.getNome(), pacienteEntity.getCpf(),
                pacienteEntity.getEmail(), pacienteEntity.getTelefone(), pacienteEntity.getEndereco());
    }
}
