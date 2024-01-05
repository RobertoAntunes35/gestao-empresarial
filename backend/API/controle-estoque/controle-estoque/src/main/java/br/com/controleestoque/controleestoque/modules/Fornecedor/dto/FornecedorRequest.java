package br.com.controleestoque.controleestoque.modules.Fornecedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FornecedorRequest {
    private Integer codigo;
    private String descricao;
}
