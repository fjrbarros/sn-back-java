package com.senior.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @Column(name = "valor_total_bruto", nullable = false)
    private double valorTotalBruto;
    @Column(name = "valor_total_liquido", nullable = false)
    private double valorTotalLiquido;
    @Column(name = "perc_imposto", nullable = false)
    private double percentualImposto;
    @ManyToMany
    @JoinTable(name = "pedido_servico", joinColumns = @JoinColumn(name = "idPedido"), inverseJoinColumns = @JoinColumn(name = "idServico"))
    private List<Servico> servicos;
}
