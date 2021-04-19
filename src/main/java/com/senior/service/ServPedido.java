package com.senior.service;

import com.senior.entity.Pedido;
import com.senior.repository.RepPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServPedido {

    private final RepPedido repPedido;

    @Autowired
    public ServPedido(RepPedido repPedido) {
        this.repPedido = repPedido;
    }

    public Pedido salvarPedido(Pedido pedido) {
        return repPedido.saveAndFlush(pedido);
    }
}
