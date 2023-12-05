import pandas as pd 
import numpy as np 
import sys 
import os

sys.path.append('C:/Users/balca/OneDrive/Desktop/Gestão/Aplicativo/backend/API/data-easy-api/src/dto')

from FornecedoresRequest import FornecedoresRequest

# from dto.FornecedoresRequest import FornecedoresRequest

class FornecedoresRepository:
    def __init__(self):
        try:
            self.data_frame = pd.read_excel('../data/D08_Fornecedor.xls')
        except Exception as error:
            print(error)
                    
    def filterAndConvertDataFrame(self):
        try:
            self.data_frame.astype({'D01_Cod_Cliente': int, 'D01_Nome': str}, copy=False).dtypes
            
            columns_to_drop = [value for value in self.data_frame.columns if value not in ['D01_Cod_Cliente', 'D01_Nome']]
            
            new_data_frame = self.data_frame.drop(columns_to_drop, axis=1)
        except Exception as error:
            print(error)
            return error
        return new_data_frame
    
    def convertDataFrameToFornecedoresResponse(self):
        data_frame = self.filterAndConvertDataFrame()
        lista_dados = []
        for index, row in data_frame.iterrows():
            # fornecedor = FornecedoresRequest(row.D01_Cod_Cliente, row.D01_Nome)
            lista_dados.append(FornecedoresRequest(row.D01_Cod_Cliente, row.D01_Nome))
        
        return lista_dados

if __name__ == '__main__':
    pass 