package br.com.dio;

import br.com.dio.persistence.migration.MigrationStrategy;
import br.com.dio.ui.MainMenu;

import java.sql.SQLException;
import java.util.Scanner;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;


public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Escolha o banco de dados:");
        System.out.println("1 - H2 (Banco em memória)");
        System.out.println("2 - MySQL");
        System.out.print("Digite sua escolha (1 ou 2): ");
        
        String choice = scanner.nextLine();
        
        System.setProperty("db.type", choice.equals("1") ? "h2" : "mysql");
        
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        }
        new MainMenu().execute();
    }

}
