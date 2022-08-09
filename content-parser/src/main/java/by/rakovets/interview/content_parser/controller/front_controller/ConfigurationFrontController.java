package by.rakovets.interview.content_parser.controller.front_controller;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.dto.ConfigurationDto;
import by.rakovets.interview.content_parser.dto.ResourceDto;
import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.service.ContentService;

public class ConfigurationFrontController implements FrontController {
    private final ConfigurationDto configurationDto;
    private final ContentService contentService;

    public ConfigurationFrontController(ConfigurationDto configurationDto, ContentService contentService) {
        this.configurationDto = configurationDto;
        this.contentService = contentService;
    }

    public void start() {
        ResourceDto[] pages = configurationDto.getPages();
        for (ResourceDto page : pages) {
            try {
                contentService.createContent(new SiteContentDto(page.getUri(), page.getxPath()), page.getFileName(),
                        configurationDto.getPathForAssets(), configurationDto.getPathForSaving());
                printDialog("File " + page.getFileName() + " was successfully saved");
            } catch (ContentParserException e) {
                printError(e.getMessage());
            }
        }
    }
}
