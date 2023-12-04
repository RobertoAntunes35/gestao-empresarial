package br.com.controleestoque.controleestoque.modules.Estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestoque.controleestoque.modules.Estoque.service.EstoqueService;
import br.com.controleestoque.controleestoque.modules.Estoque.dto.EstoqueResponse;

import java.util.List;

@RestController
@RequestMapping("api/estoque")
public class EstoqueController {
    
    @Autowired
    EstoqueService estoqueService;
    
    @RequestMapping
    public List<EstoqueResponse> findAll() {
        return estoqueService.findAll();
    }
    @RequestMapping("{id}")
    public EstoqueResponse findById(@PathVariable Integer id) {
        return estoqueService.findByIdResponse(id);
    }
}
