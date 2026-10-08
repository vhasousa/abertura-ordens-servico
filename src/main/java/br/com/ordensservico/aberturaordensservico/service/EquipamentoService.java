package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;


    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(EquipamentoRequest dadosEquipamento) {
        Optional<Setor> setorEncontrado = setorRepository.findById(dadosEquipamento.getSetorId());

        if(setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = new Equipamento();

        equipamento.setNome(dadosEquipamento.getNome());
        equipamento.setNumeroPatrimonio(dadosEquipamento.getNumeroPatrimonio());
        equipamento.setSetor(setorEncontrado.get());

        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(equipamentoEncontrado.get());
    }

    public Optional<Equipamento> atualizar(Integer id, EquipamentoRequest dadosAtualizados) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }
        
        Optional<Setor> setorEncontrado = setorRepository.findById(dadosAtualizados.getSetorId());
        
        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();

        equipamento.setNome(dadosAtualizados.getNome());
        equipamento.setNumeroPatrimonio(dadosAtualizados.getNumeroPatrimonio());
        equipamento.setSetor(setorEncontrado.get());

        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public boolean excluir(Integer id) {
        if (equipamentoRepository.existsById(id)) {
            equipamentoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
