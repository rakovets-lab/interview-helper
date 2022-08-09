package by.rakovets.interview.content_parser.client;

import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.exception.ContentParserException;

public interface HijackerClient {
    String getContentByUri(SiteContentDto siteContentDto) throws ContentParserException;
}
