package br.com.controleestoque.controleestoque.modules.Produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.controleestoque.modules.Produtos.model.ProdutoModel;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer>{
    List<ProdutoModel> findByDescricaoIgnoreCaseContaining(String descricao);
    List<ProdutoModel> findByTipoIgnoreCaseContaining(String tipo);
    List<ProdutoModel> findByGrupoIgnoreCaseContaining(String grupo);
}
