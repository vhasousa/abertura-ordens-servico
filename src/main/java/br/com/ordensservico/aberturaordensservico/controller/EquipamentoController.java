package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;

@Controller 
@RequestMapping ("/equipamentos")
public class EquipamentoController {
    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping 
    public ResponseEntity<Equipamento> cadastrar(@RequestBody Equipamento dadosEquipamento) {
        String nome = dadosEquipamento.getNome();
        String numeroPatrimonio = dadosEquipamento.getNumeroPatrimonio();
        Setor setor = dadosEquipamento.getSetor();

        Optional<Equipamento> equipamento = equipamentoService.cadastrar(nome, numeroPatrimonio, setor);
        
        if(equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(equipamento.get());
    }

    @GetMapping 
    public ResponseEntity<List<Equipamento>> listar() {
        List<Equipamento> equipamentos = equipamentoService.listar();
        
        return ResponseEntity.ok(equipamentos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Integer id) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoService.buscarPorId(id);

        if (equipamentoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(equipamentoEncontrado.get());
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Integer id, @RequestBody Equipamento dadosAtualizados) {
        Optional<Equipamento> equipamentoAtualizado = equipamentoService.atualizar(id, dadosAtualizados);

        if (equipamentoAtualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(equipamentoAtualizado.get());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        boolean excluido = equipamentoService.excluir(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


}
