package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        return repository.findAll()
                .stream()
                .map(DadosListagemMedico::new)
                .toList();
    }

    @GetMapping("/ordem")
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public void Atualizar(@PathVariable @Valid Long id, @RequestBody @Valid DadosAtualizaoMedico dados) {
        if (repository.existsById(id)) {
            var medico = repository.getReferenceById(id);
            medico.atualizarInformacoes(dados);
        }
    }
}