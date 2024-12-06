package com.example.Trabalho.repository;

import com.example.Trabalho.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(Pedido.Status status);
}
