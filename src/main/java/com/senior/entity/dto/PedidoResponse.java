package com.senior.entity.dto;

import com.senior.entity.Pedido;

import lombok.Data;

@Data
public class PedidoResponse {
    private Pedido pedido;
    private String resposta;
}
