package br.com.allan.medStar.api.domain.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(MedicoEntity medicoEntity) {
        this(medicoEntity.getId(), medicoEntity.getNome(), medicoEntity.getEmail(), medicoEntity.getCrm(), medicoEntity.getEspecialidade());
    }
}
