# �� Sistema de Gerenciamento de Kanban Board

Um sistema completo de gerenciamento de quadros Kanban desenvolvido em Java, permitindo a criação e gestão de boards, colunas e cards para organização de tarefas e projetos.

## 🚀 Funcionalidades

### Gerenciamento de Boards
- ✅ Criar novos quadros personalizados
- ✅ Selecionar e gerenciar boards existentes
- ✅ Excluir boards quando necessário
- ✅ Configuração flexível de colunas

### Sistema de Colunas
- **INITIAL**: Coluna inicial onde os cards começam
- **PENDING**: Colunas de tarefas em andamento (configuráveis)
- **FINAL**: Coluna para tarefas concluídas
- **CANCEL**: Coluna para tarefas canceladas

### Gerenciamento de Cards
- ✅ Criar cards com título e descrição
- ✅ Mover cards entre colunas
- ✅ Bloquear/desbloquear cards com motivo
- ✅ Cancelar cards
- ✅ Visualizar detalhes de cards e colunas

## 🛠️ Tecnologias Utilizadas

- **Java 21** - Linguagem principal
- **MySQL** - Banco de dados relacional (produção)
- **H2 Database** - Banco de dados em memória (desenvolvimento/testes)
- **Liquibase** - Controle de versão do banco de dados
- **Lombok** - Redução de código boilerplate
- **JDBC** - Conexão com banco de dados
- **Gradle** - Gerenciamento de dependências

## 📋 Pré-requisitos

- Java 21 ou superior
- MySQL 8.0 ou superior (apenas para produção)
- Gradle (opcional, o projeto inclui o wrapper)

**💡 Dica**: Para testes rápidos, o projeto usa H2 Database por padrão (não requer instalação).

## 🔧 Instalação e Configuração

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd asdasd
```

### 2. Configure o MySQL

#### Instalar MySQL
- **Windows**: Baixe o [MySQL Installer](https://dev.mysql.com/downloads/installer/)
- **Linux**: `sudo apt install mysql-server`
- **macOS**: `brew install mysql`

#### Criar banco e usuário
```sql
-- Conecte no MySQL como root
mysql -u root -p

-- Crie o banco de dados
CREATE DATABASE board;

-- Crie o usuário
CREATE USER 'board'@'localhost' IDENTIFIED BY 'board';

-- Dê permissões ao usuário
GRANT ALL PRIVILEGES ON board.* TO 'board'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/java/br/com/dio/persistence/config/ConnectionConfig.java`:

```java
public static Connection getConnection() throws SQLException {
    var url = "jdbc:mysql://localhost/board";
    var user = "board";        // Seu usuário MySQL
    var password = "board";    // Sua senha MySQL
    // ...
}
```

### 4. Execute o projeto
```bash
# Usando Gradle Wrapper (recomendado)
./gradlew run

# Ou usando Java diretamente
./gradlew build
java -cp build/libs/asdasd-1.0-SNAPSHOT.jar br.com.dio.Main
```

**🚀 Teste Rápido**: O projeto já está configurado para usar H2 Database. Basta executar `./gradlew run`!

## 🎯 Como Usar

### Menu Principal
```
1 - Criar um novo board
2 - Selecionar um board existente
3 - Excluir um board
4 - Sair
```

### Menu do Board
```
1 - Criar um card
2 - Mover um card
3 - Bloquear um card
4 - Desbloquear um card
5 - Cancelar um card
6 - Ver board
7 - Ver coluna com cards
8 - Ver card
9 - Voltar para o menu anterior
10 - Sair
```

### Exemplo de Fluxo

1. **Criar um Board**:
   - Nome: "Projeto Web"
   - Colunas adicionais: 2
   - Nomes das colunas: "Backlog", "Em Desenvolvimento", "Teste", "Concluído", "Cancelado"

2. **Criar Cards**:
   - Título: "Implementar Login"
   - Descrição: "Criar sistema de autenticação"

3. **Mover Cards**:
   - Cards começam na coluna inicial
   - Mova conforme o progresso do trabalho

## 📁 Estrutura do Projeto

```
src/main/java/br/com/dio/
├── dto/                    # Objetos de transferência de dados
├── exception/              # Exceções customizadas
├── persistence/            # Camada de persistência
│   ├── config/            # Configurações de conexão
│   ├── converter/         # Conversores de dados
│   ├── dao/              # Data Access Objects
│   ├── entity/           # Entidades do banco
│   └── migration/        # Estratégias de migração
├── service/              # Lógica de negócio
└── ui/                   # Interface do usuário
```

## 🗄️ Migrações do Banco

O projeto utiliza Liquibase para gerenciar as migrações do banco de dados. As migrações estão localizadas em:
- `src/main/resources/db/changelog/db.changelog-master.yml`
- `src/main/resources/db/changelog/migrations/`

## 🐛 Solução de Problemas

### Erro de Conexão com MySQL
```
Communications link failure
```
**Solução**: Verifique se o MySQL está rodando e as credenciais estão corretas.

### Erro de Driver MySQL
```
No suitable driver found
```
**Solução**: Verifique se a dependência do MySQL está no `build.gradle.kts`.

### Erro de Conexão com H2
```
Database not found
```
**Solução**: O H2 é um banco em memória, não requer configuração adicional.

## 📚 Documentação Adicional

- [Configuração de Banco de Dados](DATABASE_SETUP.md) - Guia detalhado para MySQL e H2

## 🚀 Próximas Funcionalidades

- [ ] Sistema de usuários e autenticação
- [ ] Interface web com Spring Boot
- [ ] Sistema de comentários nos cards
- [ ] Notificações e alertas
- [ ] Relatórios e métricas
- [ ] Templates de boards
- [ ] Integração com APIs externas

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

Desenvolvido como parte do curso de Java da DIO.

---

**⭐ Se este projeto te ajudou, considere dar uma estrela!**
