package com.senior.repository;

import java.util.List;

import com.senior.entity.PedidoServico;
import com.senior.entity.dto.PedidoServicoListagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepPedidoServico extends JpaRepository<PedidoServico, Long> {

    @Query(value = "SELECT pedido.id_pedido as idPedido FROM pedido", nativeQuery = true)
    public PedidoServicoListagem listarPedidoServico();
}
