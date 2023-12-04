package br.com.controleestoque.controleestoque.modules.Estoque.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controleestoque.controleestoque.modules.Estoque.model.EstoqueModel;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueResponse {
    private Integer id;
    private Integer quantidade;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:Ss")
    private LocalDateTime data_vencimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_entrada;
    private String lote;
    @JsonProperty("produto")
    private ProdutoResponse produto;

    public static EstoqueResponse of(EstoqueModel estoque) {
        return EstoqueResponse
                .builder()
                .id(estoque.getId())
                .data_entrada(estoque.getData_entrada())
                .data_vencimento(estoque.getData_vencimento())
                .quantidade(estoque.getQuantidade())
                .produto(ProdutoResponse.of(estoque.getProduto()))
                .lote(estoque.getLote())
                .build();
    }
}
