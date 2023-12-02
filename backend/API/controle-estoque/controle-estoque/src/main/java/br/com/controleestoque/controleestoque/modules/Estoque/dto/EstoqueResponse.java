package br.com.controleestoque.controleestoque.modules.Estoque.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueResponse {
    private Integer id; 
    private Integer produto_referencia; 
    private Integer quantidade;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime data_vencimento; 
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime data_entrada; 
    @JsonProperty("produto")
    private ProdutoResponse produto; 

}
