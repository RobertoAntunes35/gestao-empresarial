package br.com.controleestoque.controleestoque.modules.Produtos.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ProdutoRequest {
    private Integer codigo_primario; 
    private Integer codigo_referencia; 
    private Double comissao; 
    private String descricao; 
    private String tipo; 
    private String grupo; 
    private String unidade; 
    private Double valor_custo;
    private Double valor_venda; 
    @JsonProperty("updated_at")
    private LocalDateTime updated_at; 
    private Integer fornecedorId; 
    private Boolean controle; 
    private String ean; 
}
