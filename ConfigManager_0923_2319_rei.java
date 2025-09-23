// 代码生成时间: 2025-09-23 23:19:12
package com.example.configmanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@Component
public class ConfigManager {
    
    // Path to the configuration file
    @Value(""${config.file.path}"")
    private String configFilePath;

    // Load configuration properties from the file
    public Properties loadConfig() throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(configFilePath)) {
            properties.load(reader);
        } catch (IOException e) {
            // Log and handle the exception
            throw new IOException("Failed to load configuration properties", e);
        }
        return properties;
    }

    // Save configuration properties to the file
    public void saveConfig(Properties properties) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFilePath))) {
            properties.store(writer, null);
        } catch (IOException e) {
            // Log and handle the exception
            throw new IOException("Failed to save configuration properties", e);
        }
    }

    // Update a single property in the configuration file
    public void updateProperty(String key, String value) throws IOException {
        Properties properties = loadConfig();
        properties.setProperty(key, value);
        saveConfig(properties);
    }

    // Get the value of a property from the configuration file
    public String getProperty(String key) throws IOException {
        Properties properties = loadConfig();
        return properties.getProperty(key);
    }

    // Set the path to the configuration file
    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }
}
