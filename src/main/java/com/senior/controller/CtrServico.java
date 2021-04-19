package com.senior.controller;

import java.util.List;
import java.util.Optional;

import com.senior.entity.Servico;
import com.senior.service.ServServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/servico")
public class CtrServico {

    private final ServServico servServico;

    @Autowired
    public CtrServico(ServServico servServico) {
        this.servServico = servServico;
    }

    @PostMapping("/cadastrarServico")
    public ResponseEntity<Servico> salvarServico(@RequestBody Servico servico) {
        Servico newServico = servServico.salvarServico(servico);
        return new ResponseEntity<>(newServico, HttpStatus.CREATED);
    }

    @PutMapping("/atualizarServico")
    public ResponseEntity<Servico> atualizarServico(@RequestBody Servico servico) {

        Servico newServico = servServico.atualizarServico(servico);

        return new ResponseEntity<>(newServico, HttpStatus.CREATED);
    }

    @DeleteMapping("/removerServico/{id}")
    public ResponseEntity<String> removerServico(@PathVariable long id) {

        String msg = servServico.removerServico(id);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/recuperarServicos")
    public ResponseEntity<?> recuperarTodosServicos() {
        List<Servico> servicos = servServico.recuperarServicos();

        return new ResponseEntity<Object>(servicos, HttpStatus.OK);
    }

    @GetMapping("/recuperarServicoId/{id}")
    public ResponseEntity<?> recuperarServicoId(@PathVariable long id) {

        Optional<Servico> servico = servServico.recuperarServicoId(id);

        return new ResponseEntity<>(servico, HttpStatus.OK);
    }
}
