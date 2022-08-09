package by.rakovets.interview.content_parser.service;

import by.rakovets.interview.content_parser.client.HijackerClient;
import by.rakovets.interview.content_parser.dao.ContentDao;
import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.exception.ContentDaoException;
import by.rakovets.interview.content_parser.generators.DocumentationGenerator;
import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.factory.elements.asciidoc.ImageAscii;
import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.Element;
import by.rakovets.interview.content_parser.generators.GeneratorSelector;
import by.rakovets.interview.content_parser.model.*;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ContentServiceImpl implements ContentService {
    private final HijackerClient hijackerClient;
    private final ContentDao contentDao;
    private final FileParseServiceSelector fileParseServiceSelector;
    private final GeneratorSelector generatorSelector;

    public ContentServiceImpl(HijackerClient hijackerClient, ContentDao contentDao, FileParseServiceSelector fileParseServiceSelector,
                              GeneratorSelector generatorSelector) {
        this.hijackerClient = hijackerClient;
        this.contentDao = contentDao;
        this.fileParseServiceSelector = fileParseServiceSelector;
        this.generatorSelector = generatorSelector;
    }

    public void createContent(SiteContentDto contentDto, String fileName, String pathForAssets, String pathForSaving) throws ContentParserException {
        String contentFromClient = hijackerClient.getContentByUri(contentDto);
        FileParseService fileParseService = fileParseServiceSelector.select(contentDto,fileName);
        var fileContent = fileParseService.parse(contentFromClient, contentDto.getxPathForQuestions());
        DocumentationGenerator documentationGenerator = generatorSelector.selectFactory(fileName);
        uploadPictures(fileContent, fileName, pathForAssets);
        String generatedContent = documentationGenerator.generate(fileContent);
        Content content = new Content(fileName, pathForSaving, generatedContent);
        try {
            contentDao.save(content);
        } catch (ContentDaoException e) {
            throw new ContentParserException("Something wrong while saving file", e);
        }
    }

    public void uploadPictures(FileContent fileContent, String fileName, String pathForAssets) throws ContentParserException {
        for (Map.Entry<AbstractElement, List<Element>> kv : fileContent.getQuestionsAndAnswers().entrySet()) {
            for (Element element : kv.getValue()) {
                if (element.getClass().equals(ImageAscii.class)) {
                    String content = ((ImageAscii) element).getContent();
                    try {
                        URI uri = new URI(content);
                        URL url = uri.toURL();
                        String pictureFileName = FilenameUtils.getName(uri.getPath());
                        if (!pictureFileName.equals("")) {
                            ((ImageAscii) element).setContent(pictureFileName);
                            try (InputStream is = url.openStream()) {
                                byte[] image = is.readAllBytes();
                                String imageDir = contentDao.save(image, pictureFileName, fileName, pathForAssets);
                                fileContent.setImageDir(imageDir);
                            } catch (IOException e) {
                                throw new ContentParserException("Internal problem!", e);
                            } catch (ContentDaoException e) {
                                throw new ContentParserException(e.getMessage(), e);
                            }
                        }
                    } catch (URISyntaxException e) {
                        throw new ContentParserException("Wrong URI!", e);
                    } catch (MalformedURLException e) {
                        throw new ContentParserException("Internal problem", e);
                    }
                }
            }
        }
    }
}
