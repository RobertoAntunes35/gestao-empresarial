package br.com.controleestoque.controleestoque.modules.Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueResquest {
    private Integer produto_referencia; 
    private Integer quantidade;
    @JsonProperty("DATA_VENCIMENTO")
    private LocalDateTime data_vencimento; 
    @JsonProperty("DATA_ENTRADA")
    private LocalDateTime data_entrada; 
}
