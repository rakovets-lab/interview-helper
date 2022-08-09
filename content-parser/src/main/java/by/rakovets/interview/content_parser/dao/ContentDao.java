package by.rakovets.interview.content_parser.dao;

import by.rakovets.interview.content_parser.exception.ContentDaoException;
import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.model.Content;

public interface ContentDao {
    void save(Content content) throws ContentParserException, ContentDaoException;

    String save(byte[] image, String pictureFileName, String fileName, String directoryForAssets) throws ContentDaoException;
}
