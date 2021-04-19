package com.senior.service;

import java.util.List;

import com.senior.entity.Pedido;
import com.senior.entity.PedidoServico;
import com.senior.entity.dto.PedidoServicoDto;
import com.senior.entity.dto.PedidoServicoItens;
import com.senior.entity.dto.PedidoServicoListagem;
import com.senior.error.ResourceNotFound;
import com.senior.repository.RepPedidoServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServPedidoServico {

    private final RepPedidoServico repPedidoServico;
    private final ServPedido servPedido;

    @Autowired
    public ServPedidoServico(RepPedidoServico repPedidoServico, ServPedido servPedido) {
        this.repPedidoServico = repPedidoServico;
        this.servPedido = servPedido;
    }

    public String salvarPedidoServico(PedidoServicoDto dto) {
        validaDadosPedidoServico(dto);

        double valorTotalBruto = dto.getPedidoServicoItens().stream()
                .mapToDouble(x -> x.getValorHora() * x.getQtdHora()).sum();
        double valorImposto = valorTotalBruto - (valorTotalBruto * (dto.getPercentualImposto() / 100));
        double valorTotalLiq = valorTotalBruto - valorImposto;

        Pedido pedido = new Pedido();

        pedido.setValorTotalBruto(valorTotalBruto);
        pedido.setValorTotalLiquido(valorTotalLiq);
        pedido.setPercentualImposto(dto.getPercentualImposto());

        Pedido newPedido = servPedido.salvarPedido(pedido);

        if (newPedido == null) {
            throw new ResourceNotFound("Erro ao salvar novo pedido!");
        }

        for (PedidoServicoItens pedidoServicoItem : dto.getPedidoServicoItens()) {
            PedidoServico pedidoServico = new PedidoServico();

            pedidoServico.setNomeUsuario(dto.getNomeUsuario());
            pedidoServico.setIdServico(pedidoServicoItem.getIdServico());
            pedidoServico.setIdPedido(newPedido.getIdPedido());
            pedidoServico.setQtdHora(pedidoServicoItem.getQtdHora());
            Object newPedidoServico = repPedidoServico.saveAndFlush(pedidoServico);

            if (newPedidoServico == null) {
                throw new ResourceNotFound("Erro ao salvar pedido_servico");
            }
        }

        return "";
    }

    public void validaDadosPedidoServico(PedidoServicoDto dto) {
        if (dto.getNomeUsuario().isEmpty()) {
            throw new ResourceNotFound("Nome do usuário não informado!");
        }

        if (dto.getPercentualImposto() <= 0) {
            throw new ResourceNotFound("Porcentagem de imposto incorreto!");
        }

        if (dto.getPedidoServicoItens().size() == 0) {
            throw new ResourceNotFound("Nenhum serviço informado!");
        }

        boolean existeIdServicoNaoInformado = dto.getPedidoServicoItens().stream().filter(x -> x.getIdServico() == 0)
                .findAny() == null;
        boolean existeQtdHoraServicoNaoInformado = dto.getPedidoServicoItens().stream().filter(x -> x.getQtdHora() == 0)
                .findAny() == null;
        boolean existeValorHoraServicoNaoInformado = dto.getPedidoServicoItens().stream()
                .filter(x -> x.getValorHora() == 0).findAny() == null;

        if (existeIdServicoNaoInformado) {
            throw new ResourceNotFound("Existe um serviço informado que não possui código!");
        }

        if (existeQtdHoraServicoNaoInformado) {
            throw new ResourceNotFound("Existe um serviço informado que não possui quantidade de horas!");
        }

        if (existeValorHoraServicoNaoInformado) {
            throw new ResourceNotFound("Existe um serviço informado que não possui valor de horas!");
        }
    }

    public PedidoServicoListagem listarPedidoServico() {
        return repPedidoServico.listarPedidoServico();
    }
}
