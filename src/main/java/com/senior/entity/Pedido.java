package com.senior.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;
    @Column(name = "valor_total_bruto", nullable = false)
    private double valorTotalBruto;
    @Column(name = "valor_total_liquido", nullable = false)
    private double valorTotalLiquido;
    @Column(name = "perc_imposto", nullable = false)
    private double percentualImposto;
    @Column(name = "usuario", length = 100, nullable = false)
    private String usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private List<PedidoServico> pedidoServicos = new ArrayList<>();
}
