package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.medico.DadosCadastroMedico;
import br.com.allan.medStar.api.medico.Medico;
import br.com.allan.medStar.api.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
        repository.save(new Medico(dadosCadastroMedico));
    }
}