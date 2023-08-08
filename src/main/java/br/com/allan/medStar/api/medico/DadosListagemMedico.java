package br.com.allan.medStar.api.medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(MedicoEntity medicoEntity) {
        this(medicoEntity.getNome(), medicoEntity.getEmail(), medicoEntity.getCrm(), medicoEntity.getEspecialidade());
    }
}
