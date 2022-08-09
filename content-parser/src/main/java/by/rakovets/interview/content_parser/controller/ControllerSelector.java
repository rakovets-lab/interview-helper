package by.rakovets.interview.content_parser.controller;

import by.rakovets.interview.content_parser.controller.front_controller.ConfigurationFrontController;
import by.rakovets.interview.content_parser.controller.front_controller.FrontController;
import by.rakovets.interview.content_parser.controller.front_controller.InteractiveFrontController;
import by.rakovets.interview.content_parser.config.Configurator;
import by.rakovets.interview.content_parser.exception.ConfigurationException;
import by.rakovets.interview.content_parser.service.ContentService;
import java.io.File;

public class ControllerSelector {
    public static FrontController selectController(String[] args, ContentService contentService)  {
        FrontController frontController = new InteractiveFrontController(contentService);
        if (args.length >= 1) {
            if (new File(args[0]).exists()) {
                Configurator configurator = new Configurator();
                try {
                    frontController = new ConfigurationFrontController(configurator.configure(new File(args[0])), contentService);
                } catch (ConfigurationException e) {
                    String startMessage = "There was no configuration file found or your configuration file by" +
                            " suggested file path: " + args[0] + " is wrong";
                    frontController = new InteractiveFrontController(contentService,startMessage);
                }
            }
        } else {
            String startMessage = "There was no configuration file suggested";
            frontController = new InteractiveFrontController(contentService,startMessage);
        }
        return frontController;
    }
}