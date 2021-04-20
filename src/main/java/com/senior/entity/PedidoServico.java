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
    @Column(name = "id_pedido_servico")
    private long idPedidoServico;
    @Column(name = "qtd_hora", nullable = false)
    private int qtdHora;
    @Column(name = "id_servico", nullable = false)
    private long idServico;
}
