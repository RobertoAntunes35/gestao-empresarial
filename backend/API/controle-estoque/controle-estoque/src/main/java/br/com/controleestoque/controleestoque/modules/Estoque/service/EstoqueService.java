package br.com.controleestoque.controleestoque.modules.Estoque.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleestoque.controleestoque.config.Exception.ValidationException;
import br.com.controleestoque.controleestoque.modules.Estoque.dto.EstoqueRequest;
import br.com.controleestoque.controleestoque.modules.Estoque.dto.EstoqueResponse;
import br.com.controleestoque.controleestoque.modules.Estoque.model.EstoqueModel;
import br.com.controleestoque.controleestoque.modules.Estoque.repository.EstoqueRepository;
import br.com.controleestoque.controleestoque.modules.Produtos.service.ProdutoService;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoService produtoService;

    public List<EstoqueResponse> findAll() {
        return estoqueRepository
                .findAll()
                .stream()
                .map(EstoqueResponse::of)
                .collect(Collectors.toList());
    }

    public EstoqueModel findById(Integer id) {
        validateDataInteger(id);
        return estoqueRepository.findById(id).orElseThrow(() -> new ValidationException("Erro ao buscar pelo id."));
    }

    public EstoqueResponse findByIdResponse(Integer id) {
        return EstoqueResponse.of(findById(id));
    }

    public EstoqueResponse save(EstoqueRequest request) {
        validateEstoqueRequest(request);

        var produto = produtoService.findById(request.getProdutoId());
        var estoque = estoqueRepository.save(EstoqueModel.of(request, produto));
        return EstoqueResponse.of(estoque);
    }

    public void validateDataInteger(Integer data) {
        if (isEmpty(data)) {
            throw new ValidationException("O dado para requisição é necessário.");
        }
    }

    public void validateDataDouble(Double data) {
        if (isEmpty(data)) {
            throw new ValidationException("O dado para requisição é necessário.");
        }
    }

    public void validateDataString(String data) {
        if (isEmpty(data)) {
            throw new ValidationException("O dado para requisição é necessário.");
        }
    }

    public boolean validateEstoqueRequest(EstoqueRequest request) {
        for (Field field : request.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(request) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}