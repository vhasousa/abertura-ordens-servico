package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository,
            EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest dadosOrdemServico) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository
                .findById(dadosOrdemServico.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setDescricao(dadosOrdemServico.getDescricao());
        ordemServico.setEquipamento(equipamentoEncontrado.get());
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoRepository.findById(id);

        if (ordemServicoEncontrada.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ordemServicoEncontrada.get());
    }

    public boolean excluir(Integer id) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoRepository.findById(id);

        if (ordemServicoEncontrada.isEmpty()) {
            return false;
        }

        ordemServicoRepository.delete(ordemServicoEncontrada.get());
        return true;
    }

    public Optional<OrdemServico> atualizar(Integer id, OrdemServicoRequest dadosAtualizados) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoRepository.findById(id);

        if (ordemServicoEncontrada.isEmpty()) {
            throw new NoSuchElementException("Ordem de serviço não encontrada.");
        }

        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository
                .findById(dadosAtualizados.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            throw new NoSuchElementException("Equipamento não encontrado.");
        }

        OrdemServico ordemServico = ordemServicoEncontrada.get();

        ordemServico.setDescricao(dadosAtualizados.getDescricao());
        ordemServico.setEquipamento(equipamentoEncontrado.get());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }
}
