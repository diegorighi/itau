# Gerador de Nota Fiscal - Desafio ITAÚ

Foi um projeto muito legal de fazer, muitas tirações de sarro, muitos comentários pelo código.
Divirtam-se igual eu me diverti codando!

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- `model`: Contém as classes de modelo, como `NotaFiscal`, `Pedido`, `Item`, etc.
- `service`: Contém as classes de serviço, como `EstoqueService`, `CalculadoraAliquotaProduto`, etc.
- `chain`: Implementa o padrão Chain of Responsibility para o processamento das notas fiscais.
- `strategy`: Implementa o padrão Strategy para o cálculo de alíquotas.

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Principais Mudanças na Estrutura

### 1. Padrão Strategy

Implementei o padrão Strategy para separar as lógicas de cálculo de notas fiscais de acordo com o tipo de cliente (Pessoa Física ou Pessoa Jurídica) e Frete de acordo com a região enviada pelo JSON. Isso permite adicionar novos tipos de clientes no futuro sem alterar o código existente, bem como se o Brasil explodir ou a configuração do nosso país como nós a conhecemos mudar drasticamente hehe (não falta muito, nem do passado temos mais certeza...)

**Implementação:**

- **Interface `CalculadoraNotaFiscalStrategy`:** Define o contrato para as estratégias de cálculo PF ou PJ.
- **Classes `CalculadoraNotaFiscalPessoaFisica` e `CalculadoraNotaFiscalPessoaJuridica`:** Implementam as estratégias específicas para pessoas físicas e jurídicas, respectivamente.
- **Interface `FreteStrategy`** Define o contrato para as estratégias de cálculo do Frete
- **Classes `FreteNorteStrategy`, `FreteNordesteStrategy`, `FreteCentroOesteStrategy`, `FreteSudesteStrategy` e `FreteSulStrategy`** Implementam as estratégias específicas para as regiões do Brasil (ou do Minecraft)

### 2. Padrão Chain of Responsibility

Utilizei o padrão Chain of Responsibility para modularizar as responsabilidades na geração de notas fiscais. Cada passo do processo (cálculo de alíquotas, frete, criação da nota) é tratado por um handler específico, que pode ser configurado em uma cadeia de execução na **32** do Service `GeradorNotaFiscalServiceImpl`

**Implementação:**

- **Classe `NotaFiscalHandler`:** Classe base para os handlers na cadeia de responsabilidade.
- **Handlers específicos:** `CalculoAliquotaHandler`, `CalculoFreteHandler`, `CriacaoNotaFiscalHandler` que processam cada parte da criação da nota fiscal.

### 3. Gerenciamento de Escopo com `prototype`

Para evitar problemas de acúmulo de estado entre diferentes chamadas ao serviço de geração de notas fiscais, configuramos o bean `NotaFiscal` com o escopo `prototype`. Isso garante que uma nova instância de `NotaFiscal` seja criada a cada chamada, eliminando interferências de execuções anteriores **(APESAR DE TER ELIMINADO O STATIC DA LISTA)**
**OBS: Deixei um comentário maroto lá**

**OBRIGADO GALERA**
