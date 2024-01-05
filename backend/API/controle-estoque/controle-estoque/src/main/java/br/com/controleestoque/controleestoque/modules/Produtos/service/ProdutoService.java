package br.com.controleestoque.controleestoque.modules.Produtos.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleestoque.controleestoque.config.Exception.ValidationException;
import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorRequest;
import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;
import br.com.controleestoque.controleestoque.modules.Fornecedor.service.FornecedorService;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoDTO;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoDTOBoolean;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoDTODouble;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoDTOInteger;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoDTOString;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoRequest;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoResponse;
import br.com.controleestoque.controleestoque.modules.Produtos.model.ProdutoModel;
import br.com.controleestoque.controleestoque.modules.Produtos.repository.ProdutoRepository;

import java.lang.reflect.*;
import java.util.ArrayList;
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

    public void saveDataPythonApi(List<ProdutoRequest> request) {
        for (ProdutoRequest req : request) {
            validateProductRequest(req);
            var produto = findById(req.getCodigo_referencia());

            ArrayList<ProdutoDTO> produtosValidation = validandoProduto(req, produto);

            for (ProdutoDTO produtos : produtosValidation) {
                produtos.getCondicao();
                produtos.getField();
            }
        }

    }

    private ArrayList<ProdutoDTO> validandoProduto(ProdutoRequest produtoRequest, ProdutoModel produtoModel) {
        /*
        1"codigo_primario": 492,
        2"codigo_referencia": 49201,
        3"comissao": 3.0,
        4"controle": false,
        5"descricao": "100 LIMITE ACAI VIBE",
        6"ean": 7908484001731.0,
        7"fornecedorCodigo": 34,
        8"grupo": "DESTILADOS",
        9"tipo": "CACHACA",
        10"unidade": "UN",
        11"valor_custo": 2.0,
        12"valor_venda": 2.6
*/
        // ProdutoDTO produtoDTO = null;

        ProdutoDTODouble produtoDTODouble = new ProdutoDTODouble();
        ProdutoDTOInteger produtoDTOInteger = new ProdutoDTOInteger();
        ProdutoDTOString produtoDTOString = new ProdutoDTOString();
        ProdutoDTOBoolean produtoDTOBoolean = new ProdutoDTOBoolean();
        ArrayList<ProdutoDTO> lista_comparador = new ArrayList<>();


        if(Integer.compare(produtoRequest.getCodigo_primario(), produtoModel.getCodigo_primario()) == 0) {
            produtoDTOInteger.setCondicao(false);
            produtoDTOInteger.setField("codigo_primario");
            lista_comparador.add(produtoDTOInteger);
        
        } else {
            produtoDTOInteger.setCondicao(true);
            produtoDTOInteger.setField("codigo_primario");
            lista_comparador.add(produtoDTOInteger);
        }

        if(Integer.compare(produtoRequest.getCodigo_referencia(), produtoModel.getCodigo_referencia()) == 0) {
            produtoDTOInteger.setCondicao(false);
            produtoDTOInteger.setField("codigo_referencia");
            lista_comparador.add(produtoDTOInteger);
        } else {
            produtoDTOInteger.setCondicao(true);
            produtoDTOInteger.setField("codigo_referencia");
            lista_comparador.add(produtoDTOInteger);
        }

        if(Double.compare(produtoRequest.getValor_venda(), produtoModel.getValor_venda()) == 0) {
            produtoDTODouble.setCondicao(false);
            produtoDTODouble.setField("valor_venda");
            lista_comparador.add(produtoDTODouble);
        } else {
            produtoDTODouble.setCondicao(true);
            produtoDTODouble.setField("valor_venda");
            lista_comparador.add(produtoDTODouble);
        }

        if(Double.compare(produtoRequest.getComissao(), produtoModel.getComissao()) == 0) {
            produtoDTODouble.setCondicao(false);
            produtoDTODouble.setField("comissao");
            lista_comparador.add(produtoDTODouble);
        } else {
            produtoDTODouble.setCondicao(true);
            produtoDTODouble.setField("comissao");
            lista_comparador.add(produtoDTODouble);
        }

        if(Boolean.compare(produtoRequest.getControle(), produtoModel.getControle()) == 0) {
            produtoDTOBoolean.setCondicao(false);
            produtoDTOBoolean.setField("controle");
            lista_comparador.add(produtoDTOBoolean);
        } else {
            produtoDTOBoolean.setCondicao(true);
            produtoDTOBoolean.setField("controle");
            lista_comparador.add(produtoDTOBoolean);
        }

        if(produtoRequest.getDescricao().equals(produtoModel.getDescricao())) {
            produtoDTOString.setCondicao(false);
            produtoDTOString.setField("descricao");
            lista_comparador.add(produtoDTOString);

        } else {
            produtoDTOString.setCondicao(true);
            produtoDTOString.setField("descricao");
            lista_comparador.add(produtoDTOString);
        }


        if(produtoRequest.getEan().equals(produtoModel.getEan())) {
            produtoDTOString.setCondicao(false);
            produtoDTOString.setField("ean");
            lista_comparador.add(produtoDTOString);
        } else {
            produtoDTOString.setCondicao(true);
            produtoDTOString.setField("ean");
            lista_comparador.add(produtoDTOString);
        }

        if(Integer.compare(produtoRequest.getFornecedorCodigo(), produtoModel.getFornecedor().getCodigo()) == 0) {
            produtoDTOInteger.setCondicao(false);
            produtoDTOInteger.setField("fornecedor_codigo");
            lista_comparador.add(produtoDTOInteger);
        } else {
            produtoDTOInteger.setCondicao(true);
            produtoDTOInteger.setField("fornecedor_codigo");
            lista_comparador.add(produtoDTOInteger);
        }

        if(produtoRequest.getGrupo().equals(produtoModel.getGrupo())) {
            produtoDTOString.setCondicao(false);
            produtoDTOString.setField("grupo");
            lista_comparador.add(produtoDTOString);
        } else {
            produtoDTOString.setCondicao(true);
            produtoDTOString.setField("grupo");
            lista_comparador.add(produtoDTOString);
        }

        if(produtoRequest.getTipo().equals(produtoModel.getTipo())) {
            produtoDTOString.setCondicao(false);
            produtoDTOString.setField("tipo");
            lista_comparador.add(produtoDTOString);
        } else {
            produtoDTOString.setCondicao(true);
            produtoDTOString.setField("tipo");
            lista_comparador.add(produtoDTOString);
        }

        if(produtoRequest.getUnidade().equals(produtoModel.getUnidade())) {
            produtoDTOString.setCondicao(false);
            produtoDTOString.setField("unidade");
            lista_comparador.add(produtoDTOString);
        } else {
            produtoDTOString.setCondicao(true);
            produtoDTOString.setField("unidade");
            lista_comparador.add(produtoDTOString);
        }

        if(Double.compare(produtoRequest.getValor_custo(), produtoModel.getValor_custo()) == 0) {
            produtoDTOString.setCondicao(false);
            produtoDTOString.setField("valor_custo");
            lista_comparador.add(produtoDTOString);
        } else {
            produtoDTOString.setCondicao(true);
            produtoDTOString.setField("valor_custo");
            lista_comparador.add(produtoDTOString);
        }
    
        return lista_comparador;
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
