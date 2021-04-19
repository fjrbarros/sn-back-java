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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idServico;
    @Column(name = "nome_servico", length = 100, nullable = false)
    private String nomeServico;
    @Column(name = "valor_hora", nullable = false)
    private double valorHora;
    @ManyToMany
    @JoinTable(name = "pedido_servico", joinColumns = @JoinColumn(name = "idServico"), inverseJoinColumns = @JoinColumn(name = "idPedido"))
    private List<Pedido> pedidos;
}
