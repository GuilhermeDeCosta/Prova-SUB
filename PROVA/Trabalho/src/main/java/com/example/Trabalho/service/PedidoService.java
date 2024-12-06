package com.example.Trabalho.service;

import com.example.Trabalho.model.Pedido;
import com.example.Trabalho.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido criarPedido(Pedido pedido) {
        return repository.save(pedido);
    }

    public List<Pedido> listarPedidosAbertos() {
        return repository.findByStatus(Pedido.Status.ABERTO);
    }

    public Pedido atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));

        try {
            pedido.setStatus(Pedido.Status.valueOf(novoStatus.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido!");
        }

        return repository.save(pedido);
    }

    public void excluirPedido(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));

        if (!pedido.getStatus().equals(Pedido.Status.ABERTO)) {
            throw new RuntimeException("Apenas pedidos com status 'ABERTO' podem ser excluídos.");
        }

        repository.delete(pedido);
    }
}
