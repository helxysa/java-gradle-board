# Configuração de Banco de Dados

Este projeto suporta dois bancos de dados: **MySQL** (produção) e **H2** (desenvolvimento/testes).

##  H2 Database (Recomendado para Testes)

O H2 é um banco de dados em memória que não requer instalação. Perfeito para testes e desenvolvimento.

### Como usar:

1. **Configuração atual** (já configurado):
   - Edite `src/main/resources/database.properties`
   - Certifique-se que `db.type=h2`

2. **Executar o projeto**:
   ```bash
   ./gradlew run
   ```


## 🐬 MySQL Database (Produção)

MySQL é o banco de dados principal para uso em produção.

### Como configurar:

1. **Instalar MySQL**:
   - Windows: [MySQL Installer](https://dev.mysql.com/downloads/installer/)
   - Linux: `sudo apt install mysql-server`
   - macOS: `brew install mysql`

2. **Criar banco e usuário**:
   ```sql
   CREATE DATABASE board;
   CREATE USER 'board'@'localhost' IDENTIFIED BY 'board';
   GRANT ALL PRIVILEGES ON board.* TO 'board'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Configurar o projeto**:
   - Edite `src/main/resources/database.properties`
   - Mude `db.type=mysql`

4. **Executar**:
   ```bash
   ./gradlew run
   ```

## Arquivo de Configuração

O arquivo `src/main/resources/database.properties` controla qual banco usar:

```properties
# Para usar H2 (padrão para testes)
db.type=h2

# Para usar MySQL
# db.type=mysql
```

## Migrando entre Bancos

### De H2 para MySQL:
1. Instale o MySQL
2. Crie o banco e usuário
3. Mude `db.type=mysql` no `database.properties`
4. Execute o projeto

### De MySQL para H2:
1. Mude `db.type=h2` no `database.properties`
2. Execute o projeto

## Solução de Problemas

### Erro de Conexão H2:
- Verifique se a dependência H2 está no `build.gradle.kts`
- Certifique-se que `db.type=h2` no `database.properties`

### Erro de Conexão MySQL:
- Verifique se o MySQL está rodando
- Confirme as credenciais no `database.properties`
- Teste a conexão: `mysql -u board -p board`

##  Notas Importantes

- **H2**: Dados são perdidos ao fechar a aplicação
- **MySQL**: Dados são persistentes
- **Liquibase**: Gerencia as migrações em ambos os bancos
- **Configuração**: Centralizada no `database.properties` 