package by.rakovets.interview.content_parser.service;

import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.factory.AbstractElementFactory;
import by.rakovets.interview.content_parser.factory.FactorySelector;

public class FileParseServiceSelector {
    private final FactorySelector factorySelector;

    public FileParseServiceSelector(FactorySelector factorySelector) {
        this.factorySelector = factorySelector;
    }

    public FileParseService select(SiteContentDto siteContentDto, String fileName) {
        AbstractElementFactory abstractElementFactory = factorySelector.selectFactory(fileName);
        String domain = siteContentDto.getUri().getHost();
        String domainName = domain.startsWith("www.") ? domain.substring(4) : domain;
        if (domainName.equals("javastudy.ru")) {
            return new FileParseServiceJavaStudyXPath(abstractElementFactory);
        } else {
            return new FileParseServiceJSEHelperXpath(abstractElementFactory);
        }
    }

}
