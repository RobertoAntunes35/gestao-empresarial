package br.com.controleestoque.controleestoque.modules.Estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.controleestoque.modules.Estoque.model.EstoqueModel;;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, Integer> {

}
