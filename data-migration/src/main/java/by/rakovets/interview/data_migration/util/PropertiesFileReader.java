package by.rakovets.interview.data_migration.util;

import by.rakovets.interview.data_migration.DataMigrationApplication;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class PropertiesFileReader {
    private static final Object LOCK = new Object();
    public static final Properties PROPERTIES = new Properties();
    private static PropertiesFileReader INSTANCE = null;

    private static final String PASSWORD_KEY = "db.pass";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";
    private static final String POOL_SIZE_KEY = "db.pool.size";


    static {
        loadProperties();
    }

    public static PropertiesFileReader getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new PropertiesFileReader();
                }
            }
        }
        return INSTANCE;
    }

    public Set<Path> getPathsToFilesForParsingFromApplicationPropertiesBySuffix(String suffix) {
        Set<Path> filesPath = new HashSet<>();
        Set<String> propertyKeys = PROPERTIES.stringPropertyNames();
        for (String propertyKey : propertyKeys) {
            String property = PROPERTIES.getProperty(propertyKey);
            if (property.endsWith(suffix)) {
                filesPath.add(Paths.get(property));
            }
        }
        return filesPath;
    }

    public String getUrlKey() {
        return getPropertyByKey(URL_KEY);
    }

    public String getUserNameKey() {
        return getPropertyByKey(USERNAME_KEY);
    }

    public String getPasswordKey() {
        return getPropertyByKey(PASSWORD_KEY);
    }

    public String getPoolSizeKey() {
        return getPropertyByKey(POOL_SIZE_KEY);
    }

    private String getPropertyByKey(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (InputStream inputStream = new FileInputStream(DataMigrationApplication.fileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException();
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
