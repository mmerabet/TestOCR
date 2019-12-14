package configuration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Pattern Singleton: Read config.properties file from the resources folder.
 */
public class SingletonConfigProperties {
    private static final String CONFIG_FILENAME = "config.properties";
    private static Properties properties = new Properties();
    static {
        InputStream file;
        try {
            file = SingletonConfigProperties.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
            if (file != null) {
                properties.load(file);
            }
        } catch (IOException fnfe) {
            fnfe.printStackTrace();
        }
    }

    /**
     * Return the value of a property key.
     * @param propertyKey property key
     * @return key value of a property
     */
    public static String getPropertiesValues(String propertyKey) {
        String result = null;
        if (propertyKey != null && !propertyKey.isEmpty()) {
            result = properties.getProperty(propertyKey);
        }
        return result;
    }

    /**
     * Return all keys-values of config.properties file.
     * @return keys-values
     */
    public static Map<String,String> getProperties(){
        Map<String, String> propertyMap = new HashMap<>();
        for (String propertyKey : properties.stringPropertyNames()) {
            propertyMap.put(propertyKey, properties.getProperty(propertyKey));
        }
        return propertyMap;
    }
}

