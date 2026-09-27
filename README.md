# Halibut Truck

**Projeto final do bootcamp de Padrões de Projeto em Java.**

O Halibut Truck é uma aplicação desenvolvida em Java que simula o funcionamento de um food truck especializado em frutos do mar. A proposta do projeto foi aplicar os principais padrões de projeto a um cenário prático, utilizando como referência as operações do negócio, desde a montagem dos pedidos até o processamento do pagamento e o acompanhamento do preparo.

Desenvolvido em Java puro, sem o uso de frameworks, o projeto reúne sete padrões de projeto que trabalham de forma integrada para organizar as responsabilidades do sistema, facilitar a manutenção do código e reduzir o acoplamento entre os componentes.

## Tecnologias utilizadas

* **Java** — implementação da lógica de negócio e dos padrões de projeto.
* **Maven** — gerenciamento do projeto e execução do processo de compilação.
* **Git** — controle de versão.

## Padrões de projeto implementados

Cada padrão foi aplicado para resolver uma necessidade específica do sistema, aproximando os conceitos estudados de situações comuns no desenvolvimento de software.

| Padrão                      | Implementação                 | Aplicação no projeto                                                                                                                                |
| --------------------------- | ----------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Factory Method**          | `factory/ItemCardapioFactory` | Centraliza a criação dos itens do cardápio, como Fish and Chips, Tacos de Peixe, Limonada e Sorvete Frito.                                          |
| **Decorator**               | `decorator/`                  | Permite adicionar ingredientes e complementos aos pratos, como queijo, bacon e molho especial, sem alterar as classes originais.                    |
| **Builder**                 | `builder/Pedido`              | Facilita a construção de pedidos com diferentes itens, informações do cliente e observações, utilizando uma interface fluida.                       |
| **Chain of Responsibility** | `chain/`                      | Organiza a aplicação das regras de desconto em uma sequência de validações, permitindo que cada regra decida se o pedido atende aos seus critérios. |
| **Strategy**                | `strategy/`                   | Separa as estratégias de pagamento em dinheiro, cartão e Pix, permitindo implementar regras específicas para cada modalidade.                       |
| **Observer**                | `observer/`                   | Notifica automaticamente a cozinha e o painel do cliente quando ocorre uma alteração no status do pedido.                                           |
| **Singleton**               | `singleton/Caixa`             | Mantém uma única instância responsável pelo controle do faturamento do dia, com mecanismos de sincronização para acesso concorrente.                |

## Funcionalidades

* Criação de itens do cardápio por meio de uma fábrica.
* Personalização dos pratos com adicionais.
* Montagem de pedidos com múltiplos itens e informações do cliente.
* Aplicação de regras de desconto.
* Processamento de pagamentos por diferentes modalidades.
* Atualização do status dos pedidos com notificação automática dos interessados.
* Controle centralizado do faturamento diário.
* Simulação de um fluxo de atendimento por meio da classe `Main`.

## Estrutura do projeto

A organização do código separa as responsabilidades de cada padrão e mantém os componentes relacionados agrupados por finalidade.

```text
halibut-truck/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/halibuttruck/
                ├── Main.java
                ├── model/
                ├── factory/
                ├── decorator/
                ├── builder/
                ├── chain/
                ├── strategy/
                ├── observer/
                ├── singleton/
                └── enums/
```

* `model/`: modelos dos itens do cardápio e pratos concretos.
* `factory/`: criação dos itens do cardápio.
* `decorator/`: implementação dos adicionais dos pratos.
* `builder/`: construção dos pedidos.
* `chain/`: regras de aplicação de descontos.
* `strategy/`: estratégias de pagamento.
* `observer/`: notificações sobre mudanças no status dos pedidos.
* `singleton/`: controle do caixa.
* `enums/`: enumerações utilizadas no sistema, como o status do pedido.
* `Main.java`: execução da simulação de atendimento.

## Como executar

### Pré-requisitos

* Java JDK instalado.
* Maven instalado, caso opte pela execução com Maven.

### Opção 1 — Executar com Maven

Na raiz do projeto, execute:

```bash
mvn compile exec:java
```

Para gerar o pacote `.jar`:

```bash
mvn package
```

Caso o JAR tenha sido configurado no `pom.xml` com a classe principal e o manifesto necessários, execute-o com:

```bash
java -jar target/halibut-truck.jar
```

### Opção 2 — Executar com `javac` e `java`

Se preferir executar sem Maven, compile os arquivos Java diretamente:

```bash
find src -name "*.java" > sources.txt
javac -encoding UTF-8 -d out @sources.txt
```

Em seguida, execute a aplicação:

```bash
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out com.halibuttruck.Main
```

As opções de codificação ajudam a preservar a exibição correta de caracteres acentuados no terminal.

## Exemplo de execução

A aplicação simula a montagem de um pedido, a inclusão de adicionais, o cálculo dos valores e o processamento do pagamento.

```text
Pedido montado para Joana:
  - Fish and Chips + queijo extra + bacon crocante (R$ 38.90)
  - Tacos de Peixe + molho especial da casa (R$ 27.00)
  - Limonada Suíça (R$ 8.00)
  - Sorvete Frito (R$ 14.00)

Subtotal: R$ 87.90
Nenhum desconto aplicável a este pedido.

Pagamento de R$ 87.90 no cartão final 4321
(com taxa da maquininha: R$ 90.54).

[Cozinha] Pedido de Joana agora está: Em preparo na chapa
[Painel do cliente] Joana, seu pedido está: Em preparo na chapa
```

*Saída ilustrativa da simulação.*

## Objetivo e aprendizados

O desenvolvimento do Halibut Truck permitiu colocar em prática conceitos de programação orientada a objetos e compreender como os padrões de projeto podem ajudar a estruturar aplicações de forma mais organizada.

Durante a implementação, o foco esteve na separação de responsabilidades, na reutilização de código e na possibilidade de adicionar comportamentos sem precisar modificar constantemente as classes existentes.

Mais do que implementar os padrões individualmente, o desafio foi fazer com que eles funcionassem em conjunto dentro de um mesmo fluxo de negócio.

## Autor

**Hebert Louvores**

