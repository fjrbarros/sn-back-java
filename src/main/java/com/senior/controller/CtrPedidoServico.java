package com.senior.controller;

import java.util.List;

import com.senior.entity.dto.PedidoServicoDto;
import com.senior.entity.dto.PedidoServicoListagem;
import com.senior.service.ServPedidoServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/pedidoServico")
public class CtrPedidoServico {

    private final ServPedidoServico servPedidoServico;

    @Autowired
    public CtrPedidoServico(ServPedidoServico servPedidoServico) {
        this.servPedidoServico = servPedidoServico;
    }

    @PostMapping("/salvarPedidoServico")
    public ResponseEntity<?> salvarPedidoServico(@RequestBody PedidoServicoDto dto) {
        servPedidoServico.salvarPedidoServico(dto);

        return new ResponseEntity<>("Dados salvos com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/listarPedidoServico")
    public PedidoServicoListagem listarPedidoServico() {
        return servPedidoServico.listarPedidoServico();
    }
}
