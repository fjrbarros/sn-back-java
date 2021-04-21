package com.senior.service;

import java.util.List;

import com.senior.entity.Pedido;
import com.senior.entity.dto.PedidoResponse;
import com.senior.error.ResourceNotFound;
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

    public PedidoResponse salvarPedido(Pedido pedido) {

        validaDadosPedido(pedido);

        double valorTotal = pedido.getPedidoServicos().stream()
                .mapToDouble(x -> x.getServico().getValorHora() * x.getQtdHora()).sum();

        double porcentagem = pedido.getPercentualImposto() / 100;

        double valorLiquido = valorTotal - (valorTotal * porcentagem);

        int percLucro = (int) Math.round((valorLiquido / valorTotal) * 100);

        pedido.setValorTotalBruto(valorTotal);

        pedido.setValorTotalLiquido(valorLiquido);

        Pedido newPedido = repPedido.saveAndFlush(pedido);

        if (newPedido == null) {
            throw new ResourceNotFound("Erro ao salvar pedido!");
        }

        PedidoResponse pedidoResponse = new PedidoResponse();

        pedidoResponse.setPedido(newPedido);

        String strResp = String.format("Valor total do pedido: R$ %.2f <br> Percentual de lucro: %d%%", valorTotal,
                percLucro);

        pedidoResponse.setResposta(strResp);

        return pedidoResponse;
    }

    public List<Pedido> recuperarPedidos() {
        return repPedido.findAll();
    }

    public void validaDadosPedido(Pedido pedido) {
        if (pedido.getUsuario().isEmpty()) {
            throw new ResourceNotFound("Usuário não informado!");
        }

        if (pedido.getPercentualImposto() == 0) {
            throw new ResourceNotFound("Percentual de imposto não informado!");
        }

        if (pedido.getPedidoServicos().size() == 0) {
            throw new ResourceNotFound("Necessário informar pelo menos um serviço!");
        }

        boolean qtdHoraNaoInformado = pedido.getPedidoServicos().stream().anyMatch(x -> x.getQtdHora() == 0);

        boolean valorHoraNaoInformado = pedido.getPedidoServicos().stream()
                .anyMatch(x -> x.getServico().getValorHora() == 0);

        if (qtdHoraNaoInformado || valorHoraNaoInformado) {
            throw new ResourceNotFound("Necessário informar a quantidade de horas e o valor corretamente!");
        }
    }
}
