package br.com.allan.medStar.api.paciente;

public record DadosListagemPaciente(String nome, String cpf, String email, String telefone) {

    public DadosListagemPaciente(PacienteEntity paciente) {
        this(paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone());
    }
}
