package br.com.controleestoque.controleestoque.modules.Produtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.reflect.Field;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTODouble extends ProdutoDTO{
    private Field field;
}
