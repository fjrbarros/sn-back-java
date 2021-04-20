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
@Table(name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private long idServico;
    @Column(name = "nome_servico", length = 100, nullable = false)
    private String nomeServico;
    @Column(name = "valor_hora", nullable = false)
    private double valorHora;
}
