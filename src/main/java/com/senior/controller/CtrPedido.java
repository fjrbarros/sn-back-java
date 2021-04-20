package com.senior.controller;

import java.util.List;

import com.senior.entity.Pedido;
import com.senior.service.ServPedido;

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
@RequestMapping("api/pedido")
public class CtrPedido {

    private final ServPedido servPedido;

    @Autowired
    public CtrPedido(ServPedido servPedido) {
        this.servPedido = servPedido;
    }

    @PostMapping("/salvarPedido")
    public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {

        Pedido newPedido = servPedido.salvarPedido(pedido);

        return new ResponseEntity<Pedido>(newPedido, HttpStatus.CREATED);
    }

    @GetMapping("/recuperarPedidos")
    public ResponseEntity<?> recuperarTodosServicos() {
        List<Pedido> pedidos = servPedido.recuperarPedidos();

        return new ResponseEntity<Object>(pedidos, HttpStatus.OK);
    }

}
