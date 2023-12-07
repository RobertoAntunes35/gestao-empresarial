from typing import NamedTuple

class ProdutosRequest(NamedTuple):
    codigo_primario: int #'D04_cod
    codigo_referencia: int # Texto139
    comissao: float #Comissao
    descricao:str #D04_Descricao
    tipo: str #D04_Tipo
    grupo: str #xgrupo
    unidade: str #D04_UniPro
    valor_custo: float #D04_Precom
    valor_venda: float #D04_Precoven
    fornecedorCodigo: int #Combinação47
    controle: bool #Controle
    ean: str #gtin

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