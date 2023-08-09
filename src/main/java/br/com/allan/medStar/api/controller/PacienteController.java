package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new PacienteEntity(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public void atualizar(@PathVariable @Valid Long id, @RequestBody @Valid DadosAtualizacaoPaciente dados) {
        if (repository.existsById(id)) {
            var paciente = repository.getReferenceById(id);
            paciente.atualizarInformacoes(dados);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void Inativar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.getReferenceById(id).inativar();
        }
    }
}
