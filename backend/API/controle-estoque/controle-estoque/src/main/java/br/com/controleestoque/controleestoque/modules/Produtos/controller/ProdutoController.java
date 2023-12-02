package br.com.controleestoque.controleestoque.modules.Produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoRequest;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoResponse;
import br.com.controleestoque.controleestoque.modules.Produtos.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("{id}")
    public ProdutoResponse findById(@PathVariable Integer id){
        return produtoService.findByIdResponse(id);
    }
    
    @GetMapping
    public List<ProdutoResponse> findAll() {
        return produtoService.findAll();
    }
    @GetMapping("/tipo/{tipo}")
    public List<ProdutoResponse> findByTipo(@PathVariable String tipo) {
        return produtoService.findByTipo(tipo);
    }

    @GetMapping("/descricao/{descricao}")
    public List<ProdutoResponse> findByDescricao(@PathVariable String descricao) {
        return produtoService.findByDescricao(descricao);
    }
    @GetMapping("/grupo/{grupo}")
    public List<ProdutoResponse> findByGrupo(@PathVariable String grupo) {
        return produtoService.findByGrupo(grupo);
    }
    
    @PostMapping
    public ProdutoResponse save(@RequestBody ProdutoRequest request) {
        return produtoService.save(request);
    }
    
}
