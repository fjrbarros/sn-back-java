package com.senior.service;

import java.util.List;

import com.senior.entity.Pedido;
import com.senior.entity.PedidoServico;
import com.senior.entity.Servico;
import com.senior.error.ResourceNotFound;
import com.senior.repository.RepPedido;
import com.senior.repository.RepPedidoServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServPedido {

    private final RepPedido repPedido;
    private final RepPedidoServico repPedidoServico;

    @Autowired
    public ServPedido(RepPedido repPedido, RepPedidoServico repPedidoServico) {
        this.repPedido = repPedido;
        this.repPedidoServico = repPedidoServico;
    }

    public Pedido salvarPedido(Pedido pedido) {
        Pedido newPedido = repPedido.saveAndFlush(pedido);

        if (newPedido == null) {
            throw new ResourceNotFound("Erro ao tentar salvar o pedido!");
        }

        for (Servico servico : pedido.getServicos()) {
            PedidoServico pedidoServico = new PedidoServico();
            pedidoServico.setQtdHora(10);
            repPedidoServico.saveAndFlush(pedidoServico);
        }

        return newPedido;
    }

    public List<Pedido> recuperarPedidos() {
        return repPedido.findAll();
    }
}
