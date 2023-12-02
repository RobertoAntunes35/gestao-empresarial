package br.com.controleestoque.controleestoque.modules.Estoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.controleestoque.controleestoque.modules.Estoque.dto.EstoqueResquest;
import br.com.controleestoque.controleestoque.modules.Produtos.model.ProdutoModel;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ESTOQUE")
public class EstoqueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id; 

    @Column(name = "DATA_ENTRADA")
    private LocalDateTime data_entrada;
    
    @PrePersist
    public void PrePersist() {
        data_entrada = LocalDateTime.now();
    }

    @Column(name = "DATA_VENCIMENTO", nullable = false) 
    private LocalDateTime data_vencimento;

    @Column(name = "QUANTIDADE", nullable = false) 
    private Integer quantidade; 

    @ManyToOne
    @JoinColumn(name = "FK_PRODUTO", nullable = false)
    private ProdutoModel produto;

    public static EstoqueModel of(EstoqueResquest estoque, ProdutoModel produto) {
        return EstoqueModel
                    .builder()
                    .data_entrada(estoque.getData_entrada())
                    .data_vencimento(estoque.getData_vencimento())
                    .produto(produto)
                    .quantidade(estoque.getQuantidade())
                    .build();
                }

}
