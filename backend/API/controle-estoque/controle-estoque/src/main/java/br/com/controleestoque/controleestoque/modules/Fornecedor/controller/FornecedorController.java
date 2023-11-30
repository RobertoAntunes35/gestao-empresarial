package br.com.controleestoque.controleestoque.modules.Fornecedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorResponse;
import br.com.controleestoque.controleestoque.modules.Fornecedor.service.FornecedorService;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/descricao/{descricao}")
    public List<FornecedorResponse> findByDescription (@PathVariable String descricao) {
        return fornecedorService.findByDescricao(descricao);
    }

    @GetMapping("{id}")
    public FornecedorResponse findById(@PathVariable Integer id) {
        return fornecedorService.findByIdResponse(id);
    }

    @GetMapping
    public List<FornecedorResponse> findAll() {
        return fornecedorService.findAll();
    }
}


