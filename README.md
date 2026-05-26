# A3 Estoque Comercial

Sistema de Controle de Estoque desenvolvido em Java como trabalho avaliativo (A3) da unidade curricular **Algoritmos e Programação** da Universidade do Sul de Santa Catarina – UNISUL.

---

## Sobre o Projeto

Qualquer empresa que trabalha com produtos precisa saber o que tem no estoque, quanto custam e quando é hora de repor. Sem esse controle, é fácil perder dinheiro ou deixar clientes na mão.

Pensando nisso, desenvolvemos um sistema de controle de estoque para uma empresa comercial fictícia. O usuário consegue cadastrar produtos, registrar entradas e saídas, reajustar preços e gerar relatórios — tudo organizado em menus simples com janelas interativas. Os dados são armazenados em vetores durante a execução do programa.

---

## Requisitos Funcionais

**RF01** – O sistema deve permitir o cadastro de produtos com as informações: nome, preço unitário, unidade de medida e quantidade em estoque.

**RF02** – O sistema deve permitir a inclusão de novos produtos, validando que não existam produtos com o mesmo nome.

**RF03** – O sistema deve permitir a alteração dos dados de um produto cadastrado, exceto o nome.

**RF04** – O sistema deve permitir a consulta de um produto pelo nome, exibindo todos os seus dados.

**RF05** – O sistema deve permitir a exclusão de um produto cadastrado, mediante confirmação do usuário.

**RF06** – O sistema deve permitir o registro de entrada de produtos no estoque, atualizando automaticamente a quantidade disponível.

**RF07** – O sistema deve permitir o registro de saída de produtos do estoque, impedindo saídas maiores que a quantidade disponível.

**RF08** – O sistema deve permitir o reajuste de preço de um produto específico ou de todos os produtos, mediante informação de percentual de reajuste.

**RF09** – O sistema deve gerar o relatório Lista de Preços, exibindo todos os produtos em ordem alfabética com nome, unidade e preço.

**RF10** – O sistema deve gerar o relatório Balanço Físico-Financeiro, exibindo todos os produtos em ordem alfabética com nome, unidade, preço unitário, quantidade e preço total, além do total de itens e valor total do estoque.

**RF11** – O sistema deve exibir um menu principal com as opções: Cadastro de Produtos, Movimentação, Reajuste de Preços, Relatórios e Finalizar.

**RF12** – O sistema deve solicitar confirmação do usuário antes de realizar operações de inclusão, alteração, exclusão, movimentação e reajuste.

---

## Requisitos Não Funcionais

**RNF01** – O sistema deve ser desenvolvido na linguagem Java.

**RNF02** – O sistema deve utilizar vetores para armazenamento dos dados em memória durante a execução.

**RNF03** – O sistema deve ser desenvolvido com o uso de sub-rotinas (procedimentos e funções).

**RNF04** – A interface com o usuário deve ser baseada em janelas interativas utilizando JOptionPane.

**RNF05** – O código fonte deve seguir padrão de organização com tabulação, nomenclatura de atributos e métodos consistentes.

**RNF06** – O código deve ser documentado seguindo o padrão Javadoc.

**RNF07** – O sistema deve validar todas as entradas do usuário, exibindo mensagens de erro adequadas.

**RNF08** – O projeto deve ser versionado utilizando Git e GitHub, com commits frequentes, granulares e com mensagens descritivas.

**RNF09** – O repositório deve conter licença, README e pasta src na raiz.

**RNF10** – O desenvolvimento deve ser colaborativo, com cada integrante responsável por uma funcionalidade e realizando commits pela própria conta no GitHub.

---

## Funcionalidades

| Módulo | Descrição |
|---|---|
| Cadastro de Produtos | Incluir, alterar, consultar e excluir produtos do sistema |
| Movimentação de Estoque | Registrar entradas e saídas com atualização automática do saldo |
| Reajuste de Preços | Aplicar percentual de reajuste em um produto ou em todos de uma vez |
| Relatórios | Lista de Preços em ordem alfabética e Balanço Físico-Financeiro |
| Menu Principal | Controle do fluxo de navegação entre os módulos do programa |

---

## Tecnologias

- **Linguagem:** Java
- **IDE:** NetBeans
- **Versionamento:** Git & GitHub

---

## Equipe

| Aluno | Funcionalidade | Arquivo(s) |
|---|---|---|
| Mateus Zanela | Cadastro de Produtos | link |
| Davi Schuchowsky Boscarino de Medeiros | Movimentação | [Movimentacao.java](https://github.com/joaoluizcfonseca/A3_Estoque_Comercial/blob/main/src/Movimentacao.java) |
| Mateus Pauli Stahnke | Reajuste de Preços | link |
| Joao Luiz Candaten Fonseca | Relatórios | [Relatorios.java](https://github.com/joaoluizcfonseca/A3_Estoque_Comercial/blob/main/src/Relatorios.java) |
| Eduarda Thais Silva Brandao | Organização geral / Main | [A3_Estoque_Comercial.java](https://github.com/joaoluizcfonseca/A3_Estoque_Comercial/blob/main/src/A3_Estoque_Comercial.java) |

---

## Informações Acadêmicas

- **Instituição:** Universidade do Sul de Santa Catarina – UNISUL
- **Curso:** Ciência da Computação
- **Unidade Curricular:** Algoritmos e Programação
- **Semestre:** 2026/1

---

## Desenvolvedores

- **Mateus Zanela** RA: 10726115012 — [@MateusZanela08](https://github.com/MateusZanela08)
- **Davi Schuchowsky Boscarino de Medeiros** RA: 10726110622 — [@DaviMedeiros07](https://github.com/DaviMedeiros07)
- **Mateus Pauli Stahnke** RA: 1072611728 — [@pauli-01](https://github.com/pauli-01)
- **Joao Luiz Candaten Fonseca** RA: 10726113312 — [@joaoluizcfonseca](https://github.com/joaoluizcfonseca)
- **Eduarda Thais Silva Brandao** RA: 10726115029 — [@projetoscomerciaismadu-pixel](https://github.com/projetoscomerciaismadu-pixel)

---

## Licenca

Este projeto esta sob a licenca MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
