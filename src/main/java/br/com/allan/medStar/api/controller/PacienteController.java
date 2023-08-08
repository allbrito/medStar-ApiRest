package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.paciente.DadosCadastroPacientes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPacientes) {

    }
}
