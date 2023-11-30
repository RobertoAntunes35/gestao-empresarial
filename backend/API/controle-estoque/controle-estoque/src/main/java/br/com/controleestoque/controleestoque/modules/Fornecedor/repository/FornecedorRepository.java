package br.com.controleestoque.controleestoque.modules.Fornecedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<FornecedorModel, Integer> {
    List<FornecedorModel> findByDescricaoIgnoreCaseContaining(String descricao);
}
