package br.com.allan.medStar.api.domain.paciente;

public record DadosListagemPaciente(Long id, String nome, String cpf, String email, String telefone) {

    public DadosListagemPaciente(PacienteEntity paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone());
    }
}
