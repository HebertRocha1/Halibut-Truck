# 🐟 Halibut Truck

Projeto final do bootcamp de **Padrões de Projeto (Design Patterns)**.

A proposta foi fugir do exemplo genérico de "Animal/Forma/Veículo" e construir
um cenário único: o dia a dia de um **food truck de frutos do mar**, o
*Halibut Truck*. Cada padrão de projeto resolve um problema real desse
negócio — montar pedidos, aplicar adicionais, calcular descontos, escolher a
forma de pagamento e avisar cozinha e cliente sobre o andamento do pedido.

O projeto foi criado do zero em **Java puro** (sem framework), aplicando 7
padrões de projeto de forma integrada, não isolada.

## 🍟 Padrões aplicados

| Padrão | Onde está | Problema que resolve |
|---|---|---|
| **Factory Method** | `factory/ItemCardapioFactory` | Centraliza a criação dos pratos do cardápio (Fish and Chips, Tacos, Limonada, Sorvete Frito), sem espalhar `new` pelo código. |
| **Decorator** | `decorator/*` | Permite adicionar extras (queijo, bacon, molho especial) a qualquer prato, em qualquer combinação, sem precisar de uma subclasse para cada combinação possível. |
| **Builder** | `builder/Pedido` | Monta um pedido com vários itens, observações e cliente de forma legível e encadeada, evitando um construtor com dezenas de parâmetros. |
| **Chain of Responsibility** | `chain/*` | Aplica regras de desconto em cadeia (pedido grande, valor alto, sem desconto), cada uma decidindo se "se aplica" e repassando adiante se não. |
| **Strategy** | `strategy/*` | Isola as formas de pagamento (dinheiro, cartão, Pix), cada uma com sua própria regra (ex.: taxa da maquininha no cartão). |
| **Observer** | `observer/*` | Cozinha e painel do cliente são avisados automaticamente sempre que o status do pedido muda, sem acoplamento direto entre as partes. |
| **Singleton** | `singleton/Caixa` | Garante um único caixa controlando o faturamento do dia, com acesso seguro mesmo em cenário concorrente. |

## 📁 Estrutura

```
halibut-truck/
├── pom.xml
├── README.md
└── src/main/java/com/halibuttruck/
    ├── Main.java                 # simula um dia inteiro de atendimento
    ├── model/                    # ItemCardapio + pratos concretos
    ├── factory/                  # Factory Method
    ├── decorator/                # Decorator (extras)
    ├── builder/                  # Builder (Pedido)
    ├── chain/                    # Chain of Responsibility (descontos)
    ├── strategy/                 # Strategy (pagamento)
    ├── observer/                 # Observer (status do pedido)
    ├── singleton/                # Singleton (Caixa)
    └── enums/                    # StatusPedido
```

## ▶️ Como executar

### Opção 1 — Maven

```bash
mvn compile exec:java
```

ou gerando o `.jar` executável:

```bash
mvn package
java -jar target/halibut-truck.jar
```

### Opção 2 — Sem Maven (javac/java direto)

```bash
find src -name "*.java" > sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out com.halibuttruck.Main
```

> A flag `-encoding UTF-8` (compilação) e `-Dstdout.encoding=UTF-8` (execução)
> evitam que os acentos apareçam bugados no terminal. Rodando via Maven
> (Opção 1) isso já vem configurado por padrão.

## 💡 Exemplo de saída

```
Pedido montado para Joana:
  - Fish and Chips + queijo extra + bacon crocante (R$ 38.90)
  - Tacos de Peixe + molho especial da casa (R$ 27.00)
  - Limonada Suíça (R$ 8.00)
  - Sorvete Frito (R$ 14.00)
Subtotal: R$ 87.90
Nenhum desconto aplicável a este pedido.
Pagamento de R$ 87.90 no cartão final 4321 (com taxa da maquininha: R$ 90.54).

[Cozinha] Pedido de Joana agora está: Em preparo na chapa
[Painel do cliente] Joana, seu pedido está: Em preparo na chapa
...
```

## 🚀 Possíveis evoluções

- Expor os mesmos casos de uso como uma API REST (Spring Boot), reaproveitando
  as classes de domínio como estão;
- Persistir os pedidos em banco de dados em vez de mantê-los em memória;
- Adicionar um padrão **State** para tornar as transições de `StatusPedido`
  mais explícitas (hoje elas são controladas pelo `GerenciadorPedido`).

---

Projeto desenvolvido por **Hebert** como desafio final do bootcamp de
Padrões de Projeto.
