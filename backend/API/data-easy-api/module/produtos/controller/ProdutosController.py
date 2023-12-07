import sys 

sys.path.append('C:/Users/balca/OneDrive/Desktop/Gestão/Aplicativo/backend/API/data-easy-api/module/produtos/repository')

from ProdutosRepository import ProdutosRepository

class ProdutosController:
    def __init__(self, produtoRepository: ProdutosRepository):
        self.produtoRepository = produtoRepository

    def get_data(self):
        return self.produtoRepository.convertDataFrameToFornecedoresResponse()
    
if __name__ == '__main__':
    pass
