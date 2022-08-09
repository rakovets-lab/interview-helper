package by.rakovets.interview.content_parser.service;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.model.FileContent;

public interface ContentService {
    void createContent(SiteContentDto contentDto, String fileName, String pathForAssets, String pathForSaving) throws ContentParserException;

    void uploadPictures(FileContent fileContent, String fileName, String pathForAssets) throws ContentParserException;
}
