# Documentação de Alterações no Banco de Dados

**Projeto:** Sistema para as Lojas Rede  
**Grupo responsável pelo banco original:** Grupo do Isaque Joabe 
**Grupo desenvolvedor das alterações:** Lívia e Yasmin
**Data de início:** 07/04/2025

---

## 1. Objetivo

Este documento tem como finalidade registrar todas as modificações realizadas no banco de dados original fornecido pelo grupo do Isaque Joabe, a fim de adaptar a estrutura para o novo sistema implementado em Java com Spring Boot.

---

## 2. Estrutura Original do Banco

- Tabelas existentes:
    - `Categorias`
    - `Produtos`
    - `Fornecedores`
    - `Entrada`
    - `Saia`
    - `Funcionarios`
    - `Inventario`
    - `notas_fiscais`
    - `Clientesw`
    - `Enderecosw`
    - `Pedidos`
    - `carrinho`
    - `Pagamentos`
    - `retirada`
    - `Cliente_lj`
    - `Servicos`
    - `Funcionarios_lj`
    - `Agendamentos`
    - `Vendas`
    - `ItensVenda`
    - `transacao`
    - `notas_fisc`


- Relações principais:
    - Cada `cliente` pode ter várias `compras`
    - Cada `compra` pertence a um `cliente`
    - Cada `produto` pertence a um `fornecedor`
    - Cada `fornecedor` pode ter vários `produtos`
    - Cada `venda` pertence a um `funcionario`
    - Cada `funcionario` pode realizar várias `vendas`
    - Cada `agendamento` pertence a um `cliente`
    - Cada `cliente` pode ter vários `agendamentos`

---

## 3. Alterações Realizadas

### 3.1. Renomeação de Tabelas

- `Produtos` → `produtos`, 
- `Fornecedores` → `fornecedores`,
- `Entrada` → `entrada`, 
- `Saida` → `saida`,
- `Funcionarios` → `funcionarios`,
- `Servicos` → `servicos`,
- `Agendamentos` → `agendamentos` 
- `Vendas` → `vendas`
  *Motivo:* Mudança efetuada para o nome da tabela seguir o padrão "snake case".

---

### 3.2. Novas Tabelas Criadas

- `estoque`  
  Campos: `id`, `numero_cartao`, `bandeira`, `limite`, `cliente_id`  
  *Motivo:* Gerenciar os cartões de crédito dos clientes vinculados ao sistema.

---

### 3.3. Novas Colunas Adicionadas

- Na tabela `produtos`:
    - Adicionado `categoriaProdutos` (status ENUM)
    - Adicionado `status` (ENUM: 'PENDENTE', 'PAGA', 'ATRASADA')

---

### 3.4. Exclusão de Campos

- Tabela `funcionarios_lj`, `funcionarios`
    *Motivo:* As tabela estavam duplicadas
- Tabela `Inventario`
    *Motivo:* Essa função não irá ser implementada no sistema no momento
- Tabela `carrinho`
    *Motivo:* A tabela continha basicamente os mesmos campos da tabela Vendas, então não fazia sentido.
- Tabela `retirada`
   *Motivo:* A tabela faz parte do módulo de pedidos online e esse módulo não será implementado agora
- Tabela `ItensVenda`
   *Motivo:* A tabela continha basicamente os mesmos campos da tabela Vendas, então não fazia sentido.




- Tabela `produtos`  
    - Remoção `imagem_url` (TEXT)
    *Motivo:* Não será ser aplicado no projeto no momento.

---

### 3.5. Relações Atualizadas

- Criada nova relação: `clientes` → `cartoes_credito` (OneToMany)
- Atualizada a foreign key `transacoes.cliente_id` para refletir renomeação de `usuarios`

---

## 4.   Scripts de Alteração

**Script SQL (exemplo simplificado):**

```sql
ALTER TABLE usuarios RENAME TO clientes;

CREATE TABLE cartoes_credito (
  id BIGINT PRIMARY KEY,
  numero_cartao VARCHAR(16),
  bandeira VARCHAR(20),
  limite DECIMAL(10,2),
  cliente_id BIGINT,
  FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);