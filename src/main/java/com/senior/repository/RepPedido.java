package com.senior.repository;

import com.senior.entity.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepPedido extends JpaRepository<Pedido, Long> {

}
