# Diagnóstico 

## Violação do SRP

A classe `ServicoMatricula` está carregando 3 responsabilidades:

- **Regra de negócio**
- **Persistência**
- **Apresentação**

A regra de negócio está nas linhas 38-40 de `ServicoMatricula` e checa a média do aluno. O ideal é ter só esse trecho no método, porque, se corrigir o `String sql`, há risco de quebrar a validação da média, já que as duas estão dentro de `matricular`.

Depois ainda vem `gerarRelatorio()`, nas linhas 55-58 do mesmo arquivo, violando novamente o SRP. O ideal é a regra ficar com a regra e o SQL ficar com o SQL.

## Violação do DIP

A classe `ServicoMatricula` depende de `BancoSimulado`: se apagar o `BancoSimulado`, o serviço para de funcionar. Logo, vemos que a prioridade da classe, com sua regra de negócio, é apresentar a regra para matricular aluno, sem precisar saber os detalhes técnicos do banco de dados.

Por isso o ideal é inverter a dependência criando uma interface. Com isso, o serviço de matrícula passa a depender do contrato, e o banco passa a implementá-lo. Com o banco independente, na sua própria abstração, ele fica livre para fazer alterações, como trocar por arquivo, API ou outro banco.

## Duplicação

O acesso a dados aparece em `matricular()` e em `gerarRelatorio()`. Uma mudança na tabela obriga a alterar os dois métodos.
