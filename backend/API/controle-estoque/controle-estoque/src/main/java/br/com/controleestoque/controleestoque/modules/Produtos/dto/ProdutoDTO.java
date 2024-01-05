package br.com.controleestoque.controleestoque.modules.Produtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProdutoDTO {
    private Boolean condicao;   
    private String field;
}


