Feature: Realizar Login e Validar Livros no Site da Livraria Cultura

  Scenario:Realizar Login
    Given que acesso o site da Livraria Cultura "1"
    When clico em Entre ou Cadastre-se
    And clico em Entrar/Cadastrar a pagina de Login/Cadastro e aberta
    When clico em Entrar com Email e Senha ou Cadastre-se
    And preencho email e Senha e clico em Entrar
    Then o Login e validado e acesso a HomePage

  Scenario Outline: Consultar e Validar Dois Livros
    Given que acesso o site da Livraria Cultura <id>
    When procuro por <Livro> e pressiono Enter
    When seleciono o produto desejado e Clico no Carrinho
    Then verifico o <Nome> e o <Preco> do Livro

    Examples:
      |   id  |        Livro          |          Nome         |     Preco    |
      | " 2 " |    "CÓDIGO LIMPO"     |    "CÓDIGO LIMPO"     |  "R$ 114,00"  |
      | " 3 " |  "ARQUITETURA LIMPA"  |  "ARQUITETURA LIMPA"  |  "R$ 89,90"  |