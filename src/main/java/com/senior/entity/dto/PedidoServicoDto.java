package com.senior.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoServicoDto {
    private String nomeUsuario;
    private double percentualImposto;
    private List<PedidoServicoItens> pedidoServicoItens;
}
