package br.com.controleestoque.controleestoque.modules.Fornecedor.dto;

import org.springframework.beans.BeanUtils;

import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;
import lombok.Data;

@Data
public class FornecedorResponse {
    private Integer id;
    private String description;
    private Integer codigo;

    public static FornecedorResponse of(FornecedorModel fornecedor) {
        var response = new FornecedorResponse();
        BeanUtils.copyProperties(fornecedor, response);
        return response;
    }
}
