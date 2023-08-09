package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.domain.medico.DadosListagemMedico;
import br.com.allan.medStar.api.domain.medico.MedicoEntity;
import br.com.allan.medStar.api.domain.medico.MedicoRepository;
import br.com.allan.medStar.api.domain.medico.*;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new MedicoEntity(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosCompletosMedico(medico));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemMedico>> listarTodos() {
         var listAll = repository.findAll()
                .stream()
                .map(DadosListagemMedico::new)
                .toList();

        return ResponseEntity.ok(listAll);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<DadosListagemMedico>> listarAtivos() {
        var listAtivos =  repository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemMedico::new)
                .toList();

        return ResponseEntity.ok(listAtivos);
    }

    @GetMapping("/ativos/ordem")
    public ResponseEntity<Page<DadosListagemMedico>> listarAtivosEmOrdem(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var listPage = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(listPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCompletosMedico> buscar(@PathVariable Long id){
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();

        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosCompletosMedico(medico));
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity Atualizar(@PathVariable @Valid Long id, @RequestBody @Valid DadosAtualizaoMedico dados) {
        if (repository.existsById(id)) {
            var medico = repository.getReferenceById(id);
            medico.atualizarInformacoes(dados);

            return ResponseEntity.ok(new DadosCompletosMedico(medico));
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