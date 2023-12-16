package br.com.controleestoque.controleestoque.modules.Fornecedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorRequest;
import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorResponse;
import br.com.controleestoque.controleestoque.modules.Fornecedor.service.FornecedorService;


@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/setdata/all")
    public List<FornecedorRequest> getData() {
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<List<FornecedorRequest>> responseEntity = restTemplate.exchange(
            "http://localhost:8083/api/fornecedores/all",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference <List<FornecedorRequest>>() {
                
            });
            fornecedorService.saveData(responseEntity.getBody());
            return responseEntity.getBody();        
    }
    
    @GetMapping("/descricao/{descricao}")
    public List<FornecedorResponse> findByDescription (@PathVariable String descricao) {
        return fornecedorService.findByDescricao(descricao);
    }

    @GetMapping("{id}")
    public FornecedorResponse findById(@PathVariable Integer id) {
        return fornecedorService.findByIdResponse(id);
    }

    @GetMapping("/codigo_fornecedor/{codigo_fornecedor}")
    public FornecedorResponse findByCodigoFornecedorResponse(@PathVariable Integer codigo_fornecedor) {
        return fornecedorService.findByCodigoFornecedorResponse(codigo_fornecedor);
    }

    @GetMapping
    public List<FornecedorResponse> findAll() {
        return fornecedorService.findAll();
    }
}


