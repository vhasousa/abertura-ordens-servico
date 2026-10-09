package br.com.ordensservico.aberturaordensservico.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "ordem_servico")
public class OrdemServico {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private LocalDateTime dataAbertura;

    @ManyToOne 
    @JoinColumn (name = "equipamento_id", nullable = false)  
    private Equipamento equipamento;

    public OrdemServico() {
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    } 

    public Equipamento getEquipamento() {
        return equipamento;
    }
}
