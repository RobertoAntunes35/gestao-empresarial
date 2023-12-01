package br.com.controleestoque.controleestoque.modules.Produtos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.controleestoque.controleestoque.modules.Fornecedor.model.FornecedorModel;
import br.com.controleestoque.controleestoque.modules.Produtos.dto.ProdutoRequest;
import jakarta.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUTOS_GERAL")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO_PRIMARIO", nullable = false)
    private Integer codigo_primario;

    @Column(name = "CODIGO_REFERENCIA", nullable = false)
    private Integer codigo_referencia;
    
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "UNIDADE", nullable = false)
    private String unidade;

    @Column(name = "VALOR_CUSTO", nullable = false)
    private Double valor_custo;

    @Column(name = "VALOR_VENDA", nullable = false)
    private Double valor_venda;

    @Column(name = "COMISSAO", nullable = false)
    private Double comissao; 

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "GRUPO", nullable = false)
    private String grupo; 

    @ManyToOne
    @JoinColumn(name = "FK_FORNECEDOR", nullable = false)
    private FornecedorModel fornecedor; 

    @Column(name = "UPDATED_AT")
    private LocalDateTime updated_at;

    @PrePersist
    public void PrePersist() {
        updated_at = LocalDateTime.now();
    }

    public static ProdutoModel of(ProdutoRequest request, FornecedorModel fornecedor) {
        return ProdutoModel
            .builder()
            .codigo_primario(request.getCodigo_primario())
            .codigo_referencia(request.getCodigo_referencia())
            .comissao(request.getComissao())
            .descricao(request.getDescricao())
            .tipo(request.getTipo())
            .grupo(request.getGrupo())
            .unidade(request.getUnidade())
            .valor_custo(request.getValor_custo())
            .valor_venda(request.getValor_venda())
            .updated_at(request.getUpdated_at())
            .fornecedor(fornecedor)
            .build();
    }
}
