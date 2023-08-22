package br.com.allan.medStar.api.controller;

import br.com.allan.medStar.api.domain.consulta.AgendamentoService;
import br.com.allan.medStar.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.allan.medStar.api.domain.consulta.DadosCancelamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private AgendamentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        service.agendar(dados);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        service.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
