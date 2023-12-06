from typing import NamedTuple

class ProdutoRequest(NamedTuple):
    codigo_primario: int
    codigo_referencia: int
    comissao: float
    descricao:str
    tipo: str
    grupo: str
    unidade: str
    valor_custo: float
    valor_venda: float
    fornecedorCodigo: int
    controler: bool
    ean: str

'''
private Integer codigo_primario; 
    private Integer codigo_referencia; 
    private Double comissao; 
    private String descricao; 
    private String tipo; 
    private String grupo; 
    private String unidade; 
    private Double valor_custo;
    private Double valor_venda; 
    @JsonProperty("updated_at")
    private LocalDateTime updated_at; 
    private Integer fornecedorId; 
    private Boolean controle; 
    private String ean; 

'''