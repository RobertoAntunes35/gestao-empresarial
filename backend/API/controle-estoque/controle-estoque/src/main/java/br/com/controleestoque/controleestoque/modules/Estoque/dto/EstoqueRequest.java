package br.com.controleestoque.controleestoque.modules.Estoque.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EstoqueRequest {
    private String lote;
    private Integer quantidade;
    @JsonProperty("DATA_VENCIMENTO")
    private LocalDateTime data_vencimento;
    @JsonProperty("DATA_ENTRADA")
    private LocalDateTime data_entrada;
    private Integer produtoId;
}
