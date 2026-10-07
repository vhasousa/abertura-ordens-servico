package br.com.ordensservico.aberturaordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "equipamento") 
public class Equipamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "Nome do equipamento é obrigatório!")
    private String nome;
    
    @NotBlank (message = "Número do patrimônio do equipamento é obrigatório!")
    private String numeroPatrimonio;
    
    @ManyToOne 
    @JoinColumn (name = "setor_id", nullable = false)
    @NotNull  (message = "Setor do equipamento é obrigatório!")
    Setor setor;

    public Equipamento() {}

    public Equipamento(String nome, String numeroPatrimonio, Setor setor) {
        this.nome = nome;
        this.numeroPatrimonio = numeroPatrimonio;
        this.setor = setor;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
