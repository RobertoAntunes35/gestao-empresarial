package br.com.controleestoque.controleestoque.modules.Fornecedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;
import feign.Param;

public interface FornecedorRepository extends JpaRepository<FornecedorModel, Integer> {
    List<FornecedorModel> findByDescricaoIgnoreCaseContaining(String descricao);

    @Query(
        value="select * from FORNECEDORES f where f.codigo= :codigo_fornecedor", nativeQuery=true)
    FornecedorModel findByCodigoFornecedor(@Param("codigo_fornecedor") Integer codigo_fornecedor);


}
