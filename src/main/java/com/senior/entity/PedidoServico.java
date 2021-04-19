package com.senior.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido_servico")
public class PedidoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPedidoServico;
    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;
    @Column(name = "qtd_hora", nullable = false)
    private int qtdHora;
    @Column(nullable = false)
    private long idServico;
    @Column(nullable = false)
    private long idPedido;
}
