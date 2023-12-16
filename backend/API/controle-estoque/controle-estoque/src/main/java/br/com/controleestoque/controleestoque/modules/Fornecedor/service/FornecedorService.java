package br.com.controleestoque.controleestoque.modules.Fornecedor.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleestoque.controleestoque.config.Exception.ValidationException;
import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorRequest;
import br.com.controleestoque.controleestoque.modules.Fornecedor.dto.FornecedorResponse;
import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;
import br.com.controleestoque.controleestoque.modules.Fornecedor.repository.FornecedorRepository;

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

    public void saveData(List<FornecedorRequest> resp) {
        for (FornecedorRequest response : resp) {
            FornecedorModel foundFornecedorModel = findByCodigoFornecedor(response.getCodigo());
            
            
            if(!isEmpty(foundFornecedorModel) && validandoFornecedor(response, foundFornecedorModel)) {
                fornecedorRepository.save(FornecedorModel.of(response));
            }
        }
    }

    private boolean validandoFornecedor(FornecedorRequest fornecedorRequest, FornecedorModel fornecedorModel) {
        System.out.println(fornecedorRequest.getDescricao());
        System.out.println(fornecedorModel.getDescricao());
        if(fornecedorRequest.getDescricao().equals(fornecedorModel.getDescricao())) {
            return false;
        }
        else {
            return true;
        }
    }
    // public void saveData(List<FornecedorResponse> responses) {
    //     for(FornecedorResponse response : responses) {
    //         System.out.println(response.getDescricao());
    //         System.out.println(response.getCodigo());
    //     }
    // }
}
