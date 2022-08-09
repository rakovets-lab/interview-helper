import by.rakovets.interview.content_parser.client.HijackerClient;
import by.rakovets.interview.content_parser.client.HijackerClientHttp;
import by.rakovets.interview.content_parser.dao.ContentDao;
import by.rakovets.interview.content_parser.dao.ContentDaoFileSystem;
import by.rakovets.interview.content_parser.exception.ConfigurationException;
import by.rakovets.interview.content_parser.factory.FactorySelector;
import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.generators.GeneratorSelector;
import by.rakovets.interview.content_parser.service.*;

import java.io.IOException;

public class ContentParserApplicationTest {
    public static void main(String[] args) throws ContentParserException, IOException, ConfigurationException {
        HijackerClient hijackerClient = new HijackerClientHttp();
        ContentDao contentDao = new ContentDaoFileSystem();
        FactorySelector factorySelector = new FactorySelector();
        FileParseServiceSelector fileParseServiceSelector = new FileParseServiceSelector(factorySelector);
        GeneratorSelector generatorSelector = new GeneratorSelector();
        ContentService contentService = new ContentServiceImpl(hijackerClient, contentDao, fileParseServiceSelector, generatorSelector);
        SiteContentDto contentDto =
                new SiteContentDto("https://javastudy.ru/interview/collections/",  ".//h4");
        String fileName = "test.csv";
        String pathForAssets = "./assets/";
        String savingPath = "./output-files/";
        contentService.createContent(contentDto,fileName,pathForAssets, savingPath);
    }
}
