package br.com.allan.medStar.api.domain.medico;

import br.com.allan.medStar.api.domain.endereco.Endereco;

public record DadosCompletosMedico(

        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosCompletosMedico(MedicoEntity medicoEntity) {
        this(medicoEntity.getId(), medicoEntity.getNome(), medicoEntity.getEmail(), medicoEntity.getTelefone(),
                medicoEntity.getCrm(), medicoEntity.getEspecialidade(), medicoEntity.getEndereco());
    }
}
