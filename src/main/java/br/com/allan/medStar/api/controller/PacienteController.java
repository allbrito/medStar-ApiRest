package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new PacienteEntity(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCompletosPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemPaciente>> listarTodos() {
        var listAll = repository.findAll()
                .stream()
                .map(DadosListagemPaciente::new)
                .toList();

        return ResponseEntity.ok(listAll);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<DadosListagemPaciente>> listarAtivos() {
        var listAtivos = repository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemPaciente::new)
                .toList();

        return ResponseEntity.ok(listAtivos);
    }

    @GetMapping("/ativos/ordem")
    public ResponseEntity<Page<DadosListagemPaciente>> listarAtivosEmOrdem(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        var listPage = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);

        return ResponseEntity.ok(listPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCompletosPaciente> buscar(@PathVariable Long id){
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();

        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosCompletosPaciente(paciente));
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable @Valid Long id, @RequestBody @Valid DadosAtualizacaoPaciente dados) {
        if (repository.existsById(id)) {
            var paciente = repository.getReferenceById(id);
            paciente.atualizarInformacoes(dados);

            return ResponseEntity.ok(new DadosCompletosPaciente(paciente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity Inativar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.getReferenceById(id).inativar();

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
