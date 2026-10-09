package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdemServicoRequest {
    @NotBlank (message = "A descrição da ordem de serviço é obrigatória.")
    private String descricao;

    @NotNull (message = "O ID do equipamento é obrigatório.")
    private Integer equipamentoId;

    public OrdemServicoRequest() {
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEquipamentoId(Integer equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getEquipamentoId() {
        return equipamentoId;
    }
}
