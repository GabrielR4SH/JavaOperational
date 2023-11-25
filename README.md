# JavaOperational - Teste Backend

Este projeto é um sistema simples de compra implementado em Java como parte de um teste backend. A aplicação foi organizada em duas pastas principais para melhor organização.

## Estrutura do Projeto

- `src`: Contém o código-fonte da aplicação Java.
- `model`: Contém as classes das entidades do projeto
- `service`: Contém os serviços e funções organizados para realizar a regra de negocio

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. **Autenticação de Usuário:**
   - Ao iniciar a aplicação, o usuário é solicitado a fornecer um nome de usuário e senha.
   - A senha é digitada de forma segura, sem ser exibida no console.

2. **Operações do Cliente:**
   - Realizar compras em diferentes empresas, selecionando produtos disponíveis.
   - Visualizar um resumo do carrinho antes de finalizar a compra.
   - Escolher a empresa desejada para concluir a compra.
   - Receber um resumo da compra, incluindo o valor total, taxa de comissão da empresa e comissão do sistema.
   - Visualizar o histórico de compras realizadas.

3. **Operações da Empresa:**
   - Visualizar as vendas realizadas pela empresa, incluindo detalhes como produtos vendidos, valor total e taxa de comissão do sistema.
   - Visualizar a lista de produtos disponíveis para venda.

4. **Operações do Administrador:**
   - Realizar as mesmas operações que um cliente, incluindo compras e visualização do histórico.
   - Visualizar as vendas realizadas por todas as empresas.

## Organização do Código

O código-fonte está estruturado em várias classes para facilitar a manutenção e entendimento do sistema. As principais classes incluem:
- `Usuario`: Representa um usuário do sistema, com diferentes papéis (cliente, empresa, admin).
- `Cliente`: Representa um cliente do sistema, com informações pessoais.
- `Empresa`: Representa uma empresa no sistema, com informações sobre taxas de comissão e saldo.
- `Produto`: Representa um produto disponível para compra, associado a uma empresa.
- `Venda`: Representa uma transação de compra, contendo informações sobre os produtos, valor total e comissões.

As operações específicas para clientes, empresas e administradores são gerenciadas pelos serviços `OperacoesService` e `SistemaCompra`.

## Execução da Aplicação

A aplicação pode ser executada a partir do arquivo `Main.java`. Ao iniciar, o usuário será solicitado a fornecer um nome de usuário e senha. Após a autenticação bem-sucedida, o menu principal será exibido com as opções disponíveis.

**Nota:** A exibição da senha durante a entrada é ocultada por motivos de segurança.

Certifique-se de ter o ambiente Java configurado corretamente para executar o aplicativo.

![LaravelDocker (1)](https://github.com/GabrielR4SH/JavaOperational/assets/59832080/e97414bb-fd94-4251-81fa-66afb15c3460)


```bash
java Main
