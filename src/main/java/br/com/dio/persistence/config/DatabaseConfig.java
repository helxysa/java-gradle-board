package br.com.dio.persistence.config;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DatabaseConfig {

    private static final Properties properties = loadProperties();
    
    public static final String DB_TYPE = getDbType();
    
    public static boolean isH2() {
        return "h2".equalsIgnoreCase(DB_TYPE);
    }
    
    public static boolean isMySQL() {
        return "mysql".equalsIgnoreCase(DB_TYPE);
    }
    
    private static String getDbType() {
        // Prioriza a propriedade do sistema (definida pelo usuário)
        String systemProperty = System.getProperty("db.type");
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            return systemProperty;
        }
        // Fallback para o arquivo de propriedades
        return properties.getProperty("db.type", "mysql");
    }
    
    private static Properties loadProperties() {
        var props = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.out.println("Arquivo database.properties não encontrado, usando configurações padrão (MySQL)");
        }
        return props;
    }
} 