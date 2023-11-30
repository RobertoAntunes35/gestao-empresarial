package br.com.controleestoque.controleestoque.modules.Fornecedor.model;

import org.springframework.beans.BeanUtils;

import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorRequest;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FORNECEDORES")
public class FornecedorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "CODIGO", nullable = false)
    private Integer codigo;

    public static FornecedorModel of(FornecedorRequest request) {
        var response = new FornecedorModel();
        BeanUtils.copyProperties(request, response);
        return response;
    }
}
