import pandas as pd 
import numpy as np 
import sys 
import os
import json 
sys.path.append('C:/Users/balca/OneDrive/Desktop/Gestão/Aplicativo/backend/API/data-easy-api/module/fornecedores/dto')

from FornecedoresRequest import FornecedoresRequest

class FornecedoresRepository:
    def __init__(self):
        try:
            self.data_frame = pd.read_excel('src/data/D08_Fornecedor.xls')
        except Exception as error:
            print(error)
                    
    def filterAndConvertDataFrame(self):
        try:
            self.data_frame.astype({'D01_Cod_Cliente': int, 'D01_Nome': str}, copy=False).dtypes
            
            columns_to_drop = [value for value in self.data_frame.columns if value not in ['D01_Cod_Cliente', 'D01_Nome']]
            
            new_data_frame = self.data_frame.drop(columns_to_drop, axis=1).dropna()
        except Exception as error:
            print(error)
            return error
        return new_data_frame
    
    def convertDataFrameToFornecedoresResponse(self):
        data_frame = self.filterAndConvertDataFrame()
        listaDados = []
        for _, row in data_frame.iterrows():
            fornecedorRequest = FornecedoresRequest(row.D01_Nome, row.D01_Cod_Cliente)
            
            fornecedor = {
                'codigo':fornecedorRequest.codigo,
                'descricao':fornecedorRequest.descricao,
            }
            listaDados.append(fornecedor)
        return listaDados

if __name__ == '__main__':
    pass 