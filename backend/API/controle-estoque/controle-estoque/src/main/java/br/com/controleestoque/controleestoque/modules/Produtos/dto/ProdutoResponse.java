package br.com.controleestoque.controleestoque.modules.Produtos.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorResponse;
import br.com.controleestoque.controleestoque.modules.Produtos.model.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
    private Integer id; 
    private Integer codigo_primario; 
    private Integer codigo_referencia; 
    private Double comissao; 
    private String descricao; 
    private String tipo; 
    private String grupo; 
    private String unidade; 
    private Double valor_custo;
    private Double valor_venda; 
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updated_at; 
    private Boolean controle; 
    private String ean; 
    @JsonProperty("fornecedor")
    private FornecedorResponse fornecedor;

    public static ProdutoResponse of(ProdutoModel request) {
        return ProdutoResponse
                    .builder()
                    .id(request.getId())
                    .codigo_primario(request.getCodigo_primario())
                    .codigo_referencia(request.getCodigo_referencia())
                    .comissao(request.getComissao())
                    .descricao(request.getDescricao())
                    .tipo(request.getTipo())
                    .grupo(request.getGrupo())
                    .unidade(request.getUnidade())
                    .valor_custo(request.getValor_custo())
                    .valor_venda(request.getValor_venda())
                    .updated_at(request.getUpdated_at())
                    .controle(request.getControle())
                    .ean(request.getEan())
                    .fornecedor(FornecedorResponse.of(request.getFornecedor()))
                    .build();
                }
}
