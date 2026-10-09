package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody OrdemServicoRequest ordemServico) {
        Optional<OrdemServico> novaOrdemServico = ordemServicoService.cadastrar(ordemServico);

        if (novaOrdemServico.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Equipamento não encontrado. Não é possível criar a ordem de serviço.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novaOrdemServico.get());
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {
        List<OrdemServico> ordensServico = ordemServicoService.listar();

        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoService.buscarPorId(id);

        if (ordemServicoEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ordemServicoEncontrada.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id,
            @Valid @RequestBody OrdemServicoRequest ordemServico) {
        try {
            Optional<OrdemServico> ordemServicoAtualizada = ordemServicoService.atualizar(id, ordemServico);
            if (ordemServicoAtualizada.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(ordemServicoAtualizada.get());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        boolean excluida = ordemServicoService.excluir(id);

        if (!excluida) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
