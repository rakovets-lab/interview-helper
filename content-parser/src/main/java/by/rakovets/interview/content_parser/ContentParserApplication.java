package by.rakovets.interview.content_parser;

import by.rakovets.interview.content_parser.client.HijackerClient;
import by.rakovets.interview.content_parser.client.HijackerClientHttp;
import by.rakovets.interview.content_parser.controller.ControllerSelector;
import by.rakovets.interview.content_parser.controller.front_controller.FrontController;
import by.rakovets.interview.content_parser.dao.ContentDao;
import by.rakovets.interview.content_parser.dao.ContentDaoFileSystem;
import by.rakovets.interview.content_parser.generators.DocumentationGenerator;
import by.rakovets.interview.content_parser.factory.FactorySelector;
import by.rakovets.interview.content_parser.generators.DocumentationGeneratorAscii;
import by.rakovets.interview.content_parser.generators.GeneratorSelector;
import by.rakovets.interview.content_parser.service.*;

public class ContentParserApplication {
    public static void main(String[] args) {
        HijackerClient hijackerClient = new HijackerClientHttp();
        ContentDao contentDao = new ContentDaoFileSystem();
        FactorySelector factorySelector = new FactorySelector();
        FileParseServiceSelector fileParseServiceSelector = new FileParseServiceSelector(factorySelector);
        GeneratorSelector generatorSelector = new GeneratorSelector();
        ContentService contentService = new ContentServiceImpl(hijackerClient, contentDao, fileParseServiceSelector, generatorSelector);
        FrontController mainController = ControllerSelector.selectController(args,contentService);
        mainController.start();
    }
}
