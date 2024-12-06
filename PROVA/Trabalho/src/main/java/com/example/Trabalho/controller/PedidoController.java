package com.example.Trabalho.controller;

import com.example.Trabalho.dto.AtualizarStatusDTO;
import com.example.Trabalho.model.Pedido;
import com.example.Trabalho.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(201).body(service.criarPedido(pedido));
    }

    @GetMapping("/abertos")
    public ResponseEntity<List<Pedido>> listarPedidosAbertos() {
        return ResponseEntity.ok(service.listarPedidosAbertos());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusDTO statusDTO) {
        return ResponseEntity.ok(service.atualizarStatus(id, statusDTO.getNovoStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        service.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
