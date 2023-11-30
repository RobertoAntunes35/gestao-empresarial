package br.com.controleestoque.controleestoque.modules.Fornecedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FornecedorRequest {
    private String description;
    private Integer codigoFornecedor;

}
