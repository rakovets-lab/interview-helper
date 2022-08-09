package by.rakovets.interview.content_parser.service;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.model.FileContent;

public interface FileParseService {
    FileContent parse(String content, String xPathExpressionQuestions) throws ContentParserException;
}
