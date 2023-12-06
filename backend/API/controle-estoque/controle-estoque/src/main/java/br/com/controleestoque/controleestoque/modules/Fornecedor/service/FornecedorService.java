package br.com.controleestoque.controleestoque.modules.Fornecedor.service;

import org.springframework.stereotype.Service;
import static org.springframework.util.ObjectUtils.isEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.controleestoque.controleestoque.config.Exception.ValidationException;
import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorResponse;
import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;
import br.com.controleestoque.controleestoque.modules.Fornecedor.repository.FornecedorRepository;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorResponse> findByDescricao(String descricao) {
        if (isEmpty(descricao)) {
            throw new ValidationException("A descrição do fornecedor precisa ser informada.");
        }
        return fornecedorRepository
                .findByDescricaoIgnoreCaseContaining(descricao)
                .stream()
                .map(FornecedorResponse::of)
                .collect(Collectors.toList());
    }

    public FornecedorModel findById(Integer id) {
        return fornecedorRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("Erro ao solicitar consulta ao id do fornecedor"));
    }

    public FornecedorResponse findByIdResponse(Integer id){
        return FornecedorResponse.of(findById(id));
    }
    
    public FornecedorModel findByCodigoFornecedor(Integer codigo_fornecedor) {
        return fornecedorRepository.findByCodigoFornecedor(codigo_fornecedor);
    }

    public FornecedorResponse findByCodigoFornecedorResponse(Integer codigo_fornecedor) {
        return FornecedorResponse.of(findByCodigoFornecedor(codigo_fornecedor));
    }
    public List<FornecedorResponse> findAll() {
        return fornecedorRepository
                .findAll()
                .stream()
                .map(FornecedorResponse::of)
                .collect(Collectors.toList());
    }
}
