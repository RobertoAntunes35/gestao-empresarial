-- INSERT INTO FORNECEDORES (ID, CODIGO, DESCRICAO) VALUES (101, 1, 'BEBIDAS ASTECA HINOMOTO SHOYU')
-- INSERT INTO FORNECEDORES (ID, CODIGO, DESCRICAO) VALUES (102, 2, 'BELLPAR')
-- INSERT INTO FORNECEDORES (ID, CODIGO, DESCRICAO) VALUES (103, 3, 'CEPAL - CERVEJARIA PAULISTANA LTDA.')
-- INSERT INTO FORNECEDORES (ID, CODIGO, DESCRICAO) VALUES (104, 4, 'CONVENCAO SAO PAULO INDUSTRIA DE BEBIDAS E CONEXOS')
-- INSERT INTO FORNECEDORES (ID, CODIGO, DESCRICAO) VALUES (105, 5, 'EDENCOCO COMERCIAL IMPORTADORA E EXPORTADORA LTDA')

-- INSERT INTO PRODUTOS_GERAL (ID, CODIGO_PRIMARIO, CODIGO_REFERENCIA, DESCRICAO, UNIDADE, VALOR_CUSTO, VALOR_VENDA, COMISSAO, TIPO, GRUPO, CONTROLE, EAN, FK_FORNECEDOR, UPDATED_AT) VALUES (101, 196, 19606, 'GROSELHA VANNUCCI PET 6X920 ML UNID.', 'FD', 42.8, 62.30, 0.03, 'GROSELHA', 'XAROPE', 'true','786198568',102, CURRENT_TIMESTAMP)
-- INSERT INTO PRODUTOS_GERAL (ID, CODIGO_PRIMARIO, CODIGO_REFERENCIA, DESCRICAO, UNIDADE, VALOR_CUSTO, VALOR_VENDA, COMISSAO, TIPO, GRUPO, CONTROLE, EAN, FK_FORNECEDOR, UPDATED_AT) VALUES (102, 196, 19601, 'GROSELHA VANNUCCI PET 920 ML UNID.', 'UN', 7.14, 10.38, 0.03, 'GROSELHA', 'XAROPE', 'true','786198568', 102, CURRENT_TIMESTAMP)
-- INSERT INTO PRODUTOS_GERAL (ID, CODIGO_PRIMARIO, CODIGO_REFERENCIA, DESCRICAO, UNIDADE, VALOR_CUSTO, VALOR_VENDA, COMISSAO, TIPO, GRUPO, CONTROLE, EAN FK_FORNECEDOR, UPDATED_AT) VALUES (103, 458, 45801, 'REFRIGERANTE ORLANDO PET 250ML GENGI-BIRRA', 'UN', 1.06, 1.54, 0.03, 'REFRI 250ML', 'REFRIGERANTE', 'false','786198568', 102, CURRENT_TIMESTAMP)
-- INSERT INTO PRODUTOS_GERAL (ID, CODIGO_PRIMARIO, CODIGO_REFERENCIA, DESCRICAO, UNIDADE, VALOR_CUSTO, VALOR_VENDA, COMISSAO, TIPO, GRUPO, CONTROLE,EAN,FK_FORNECEDOR, UPDATED_AT) VALUES (104, 196, 45806, 'REFRIGERANTE ORLANDO PET 12X250ML GENGI-BIRRA', 'FD', 12.75, 18.5, 0.03, 'REFRI 250ML', 'REFRIGERANTE', 'true','786198568', 102, CURRENT_TIMESTAMP)

-- INSERT INTO ESTOQUE(ID, DATA_ENTRADA, DATA_VENCIMENTO, QUANTIDADE, LOTE, FK_PRODUTO) VALUES (101, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 600, 'ABCD31122023', 101)
-- INSERT INTO ESTOQUE(ID, DATA_ENTRADA, DATA_VENCIMENTO, QUANTIDADE, LOTE, FK_PRODUTO) VALUES (102, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 60, 'ABCD31122023', 102)
-- INSERT INTO ESTOQUE(ID, DATA_ENTRADA, DATA_VENCIMENTO, QUANTIDADE, LOTE, FK_PRODUTO) VALUES (103, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 160, 'ABCD31122023', 104)