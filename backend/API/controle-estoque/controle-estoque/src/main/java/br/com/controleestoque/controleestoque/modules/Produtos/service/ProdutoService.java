package br.com.controleestoque.controleestoque.modules.Produtos.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleestoque.controleestoque.config.Exception.ValidationException;
import br.com.controleestoque.controleestoque.modules.Fornecedor.service.FornecedorService;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoRequest;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoResponse;
import br.com.controleestoque.controleestoque.modules.Produtos.model.ProdutoModel;
import br.com.controleestoque.controleestoque.modules.Produtos.repository.ProdutoRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    FornecedorService fornecedorService;

    // Getters Padroes JPA
    public ProdutoModel findById(Integer id) {
        confirmDataInteger(id);
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("O id nao foi encontrado."));
    }

    public List<ProdutoResponse> findAll() {
        return produtoRepository
                    .findAll()
                    .stream()
                    .map(ProdutoResponse::of)
                    .collect(Collectors.toList());
    }
    public List<ProdutoResponse> findByDescricao(String descricao) {
        confirmDataString(descricao);
        return produtoRepository
        .findByDescricaoIgnoreCaseContaining(descricao)
        .stream()
        .map(ProdutoResponse::of)
        .collect(Collectors.toList());
    }
    public ProdutoResponse findByIdResponse(Integer id) {
        return ProdutoResponse.of(findById(id));
    }

    public List<ProdutoResponse> findByTipo(String tipo) {
        confirmDataString(tipo);

        return produtoRepository
                .findByTipoIgnoreCaseContaining(tipo)
                .stream()
                .map(ProdutoResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProdutoResponse> findByGrupo(String grupo) {
        confirmDataString(grupo);

        return produtoRepository
                .findByGrupoIgnoreCaseContaining(grupo)
                .stream()
                .map(ProdutoResponse::of)
                .collect(Collectors.toList());
    }

    public ProdutoResponse save(ProdutoRequest request) {
        validateProductRequest(request);
        var fornecedor = fornecedorService.findByCodigoFornecedor(request.getFornecedorCodigo());
        var produto = produtoRepository.save(ProdutoModel.of(request, fornecedor));
        return ProdutoResponse.of(produto);
    }

    public void confirmDataString(String data) {
        if (isEmpty(data)) {
            throw new ValidationException("O valor de parametro precisa ser informado.");
        }
    }

    public void confirmDataInteger(Integer data) {
        if (isEmpty(data)) {
            throw new ValidationException("O valor de parametro precisa ser informado.");
        }
    }

    public void confirmDataDouble(Double data) {
        if (isEmpty(data)) {
            throw new ValidationException("O valor de parametro precisa ser informado.");
        }
    }

    public boolean validateProductRequest(ProdutoRequest request) {
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
