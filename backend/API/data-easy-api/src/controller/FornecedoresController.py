import sys 

sys.path.append('C:/Users/balca/OneDrive/Desktop/Gestão/Aplicativo/backend/API/data-easy-api/src/repository')


from FornecedoresRepository import FornecedoresRepository

class FornecedoresController:
    def __init__(self, fornecedorRepository: FornecedoresRepository):
        self.fornecedorRepository = fornecedorRepository

    def get_data(self):
        return self.fornecedorRepository.convertDataFrameToFornecedoresResponse()
    
if __name__ == '__main__':
    fornecedorRepository = FornecedoresRepository()
    controlle = FornecedoresController(fornecedorRepository)
    print(controlle.get_data())
