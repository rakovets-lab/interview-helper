package by.rakovets.interview.content_parser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import by.rakovets.interview.content_parser.exception.ConfigurationException;
import by.rakovets.interview.content_parser.dto.ConfigurationDto;

import java.io.File;
import java.io.IOException;

public class Configurator {
   private final ObjectMapper mapper;

    public Configurator() {
        mapper = new ObjectMapper();
    }

    public ConfigurationDto configure(File configurationFile) throws ConfigurationException {
        ConfigurationDto configurationDto;
        try {
            configurationDto = mapper.readValue(configurationFile, ConfigurationDto.class);
        } catch (IOException e) {
            throw new ConfigurationException("Chek configuration file", e);
        }
        return  configurationDto;
    }
}
