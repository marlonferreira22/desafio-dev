# Desafio programação - para vaga desenvolvedor

Por favor leiam este documento do começo ao fim, com muita atenção.
O intuito deste teste é avaliar seus conhecimentos técnicos em programação.
O teste consiste em parsear [este arquivo de texto(CNAB)](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt) e salvar suas informações(transações financeiras) em uma base de dados a critério do candidato.
Este desafio deve ser feito por você em sua casa. Gaste o tempo que você quiser, porém normalmente você não deve precisar de mais do que algumas horas.

# Instruções de entrega do desafio

1. Primeiro, faça um fork deste projeto para sua conta no Github (crie uma se você não possuir).
2. Em seguida, implemente o projeto tal qual descrito abaixo, em seu clone local.
3. Por fim, envie via email o projeto ou o fork/link do projeto para seu contato Bycoders_ com cópia para rh@bycoders.com.br.

# Descrição do projeto

Você recebeu um arquivo CNAB com os dados das movimentações finanaceira de várias lojas.
Precisamos criar uma maneira para que estes dados sejam importados para um banco de dados.

Sua tarefa é criar uma interface web que aceite upload do [arquivo CNAB](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt), normalize os dados e armazene-os em um banco de dados relacional e exiba essas informações em tela.

**Sua aplicação web DEVE:**

1. Ter uma tela (via um formulário) para fazer o upload do arquivo(pontos extras se não usar um popular CSS Framework )
2. Interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco de dados relacional, **se atente as documentações** que estão logo abaixo.
3. Exibir uma lista das operações importadas por lojas, e nesta lista deve conter um totalizador do saldo em conta
4. Ser escrita na sua linguagem de programação de preferência
5. Ser simples de configurar e rodar, funcionando em ambiente compatível com Unix (Linux ou Mac OS X). Ela deve utilizar apenas linguagens e bibliotecas livres ou gratuitas.
6. Git com commits atomicos e bem descritos
7. PostgreSQL, MySQL ou SQL Server
8. Ter testes automatizados
9. Docker compose (Pontos extras se utilizar)
10. Readme file descrevendo bem o projeto e seu setup
11. Incluir informação descrevendo como consumir o endpoint da API

**Sua aplicação web não precisa:**

1. Lidar com autenticação ou autorização (pontos extras se ela fizer, mais pontos extras se a autenticação for feita via OAuth).
2. Ser escrita usando algum framework específico (mas não há nada errado em usá-los também, use o que achar melhor).
3. Documentação da api.(Será um diferencial e pontos extras se fizer)

# Documentação do CNAB

| Descrição do campo  | Inicio | Fim | Tamanho | Comentário
| ------------- | ------------- | -----| ---- | ------
| Tipo  | 1  | 1 | 1 | Tipo da transação
| Data  | 2  | 9 | 8 | Data da ocorrência
| Valor | 10 | 19 | 10 | Valor da movimentação. *Obs.* O valor encontrado no arquivo precisa ser divido por cem(valor / 100.00) para normalizá-lo.
| CPF | 20 | 30 | 11 | CPF do beneficiário
| Cartão | 31 | 42 | 12 | Cartão utilizado na transação 
| Hora  | 43 | 48 | 6 | Hora da ocorrência atendendo ao fuso de UTC-3
| Dono da loja | 49 | 62 | 14 | Nome do representante da loja
| Nome loja | 63 | 81 | 19 | Nome da loja

# Documentação sobre os tipos das transações

| Tipo | Descrição | Natureza | Sinal |
| ---- | -------- | --------- | ----- |
| 1 | Débito | Entrada | + |
| 2 | Boleto | Saída | - |
| 3 | Financiamento | Saída | - |
| 4 | Crédito | Entrada | + |
| 5 | Recebimento Empréstimo | Entrada | + |
| 6 | Vendas | Entrada | + |
| 7 | Recebimento TED | Entrada | + |
| 8 | Recebimento DOC | Entrada | + |
| 9 | Aluguel | Saída | - |

# Avaliação

Seu projeto será avaliado de acordo com os seguintes critérios.

1. Sua aplicação preenche os requerimentos básicos?
2. Você documentou a maneira de configurar o ambiente e rodar sua aplicação?
3. Você seguiu as instruções de envio do desafio?
4. Qualidade e cobertura dos testes unitários.

Adicionalmente, tentaremos verificar a sua familiarização com as bibliotecas padrões (standard libs), bem como sua experiência com programação orientada a objetos a partir da estrutura de seu projeto.

# Referência

Este desafio foi baseado neste outro desafio: https://github.com/lschallenges/data-engineering

---

Boa sorte!

---

# Documentação

**Tecnologias utilizadas:**

Frontend: Thymeleaf e HTML.

Backend: Java 8, SpringBoot, Maven, Swagger.

Banco de dados: MySQL e H2.

**Configuração:**

1. Faça o clone do projeto no servidor/maquina.
2. Abra em um editor de texto o arquivo application.properties que se encontra no caminho: src/main/resources/application.properties
3. Nas linhas 11 e 12, insira os dados de conexão com o seu MySQL. 
4. Caso o seu banco de dados não esteja no localhost, edite a linha 7 informando o ip e porta do seu MySQL.
5. Salve o arquivo.
6. Acesse seu MySQL e execute a seguinte query: CREATE SCHEMA `desafio_db` ;
7. Vá para o diretorio /src do projeto e digite o comando: mvn clean install .Caso retorne erro, será necessário instalar o Maven. 
    - No Mac basta executar o comando: brew install maven
    - No Linux (Ubuntu) execute o comando: sudo apt install maven
8. Quando o item 7 terminar, será criado um novo diretório chamado "target", acesse este diretório.
9. Deverá ter sido criado o arquivo "Desafio-0.0.1-SNAPSHOT.jar"
10. Em seguida, basta digitar o comando: java -jar Desafio-0.0.1-SNAPSHOT.jar e o projeto estará sendo executado.

**Acessos:**

Para acessar via navegador, basta informar o endereço: http://localhost:8777/ .

Foi gerada automaticamente a documentação do endpoints utilizando Swagger, que pode ser acessada pelo endereço: http://localhost:8777/swagger-ui.html#/API .
Para o consumo da API, segue exemplo de endereço: http://localhost:8777/api/financeiro 

**Sobre o projeto:**

Após realizar a configuração e executar o projeto, será possível acessá-lo via navegador pelo endereço http://localhost:8777/ esta página é carregada da seguinte forma:

A requisição chega no controller chamado DocController que é responsável por carregar a página executando a função get().
Quando é feito o upload do arquivo, esta requisição invoca o DocController função uploadMultipleFiles(). Esta função é responsável por realizar a leitura do arquivo, normalização dos dados, salvar no banco de dados e por fim realizar um redirect para que a página principal carregue as informações.

Ainda na tela principal, quando os dados são carregados na tabela, é exibida as informações: Loja, Saldo e Link 
 - Loja: Nome do estabelecimento
 - Saldo: Resultado do cálculo feito entre as operações de entradas e saídas contidas no arquivo.
 - Link: Endereço para redirecionar para a página contendo os detalhes de cada operação.


