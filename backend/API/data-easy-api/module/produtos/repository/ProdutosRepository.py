import pandas as pd 
import numpy as np 
import sys 
import os
import json 
sys.path.append('C:/Users/balca/OneDrive/Desktop/Gestão/Aplicativo/backend/API/data-easy-api/module/produtos/dto')

from ProdutosRequest import ProdutosRequest

class ProdutosRepository:
    def __init__(self):
        try:
            self.data_frame = pd.read_excel('module/data/D04_Produto_Completo.xls')
        except Exception as e:
            return {
                "message":"Erro ao importar dataframe.",
                "error":str(e)
            }

    def filterAndConvertDataFrame(self):
        try:
            self.data_frame.astype({'D04_cod': int, 'Texto139': int, 'Comissao':float, 'D04_Descricao': str,
                                    'D04_Tipo':str, 'xgrupo':str, 'D04_UniPro':str, 'D04_Precom': float,
                                    'D04_Precoven':float, 'Combinação47':int, 'Controle':bool, 'gtin':str}, copy=False).dtypes
            
            columns_to_drop = [value for value in self.data_frame.columns if 
                               value not in ['D04_cod', 'Texto139', 'Comissao', 'D04_Descricao',
                                            'D04_Tipo', 'xgrupo', 'D04_UniPro', 'D04_Precom',
                                            'D04_Precoven', 'Combinação47', 'Controle', 'gtin']]
            
            new_data_frame = self.data_frame.drop(columns_to_drop, axis=1).dropna()
        except Exception as e:
            return {
                "message":"Erro ao filtrar e converter dataframe.",
                "error":str(e)
            }
        return new_data_frame
    
    def convertDataFrameToFornecedoresResponse(self):
        data_frame = self.filterAndConvertDataFrame()
        listaDados = []
        for index, row in data_frame.iterrows():
            produtoRequest = ProdutosRequest(
                codigo_primario = row.D04_cod,
                codigo_referencia = row.Texto139,
                comissao = row.Comissao,
                descricao = row.D04_Descricao,
                tipo = row.D04_Tipo,
                grupo = row.xgrupo,
                unidade = row.D04_UniPro,
                valor_custo = row.D04_Precom,
                valor_venda = row.D04_Precoven,
                fornecedorCodigo = row.Combinação47,
                controle = row.Controle,
                ean = row.gtin)
            
            produto = {
                'codigo_primario':produtoRequest.codigo_primario,
                'codigo_referencia':produtoRequest.codigo_referencia,
                'comissao':produtoRequest.comissao,
                'descricao':produtoRequest.descricao,
                'tipo':produtoRequest.tipo,
                'grupo':produtoRequest.grupo,
                'unidade':produtoRequest.unidade,
                'valor_custo':produtoRequest.valor_custo,
                'valor_venda':produtoRequest.valor_venda,
                'fornecedorCodigo':produtoRequest.fornecedorCodigo,
                'controle':produtoRequest.controle,
                'ean':produtoRequest.ean,
            }
            listaDados.append(produto)
        return listaDados

if __name__ == '__main__':
    pass 