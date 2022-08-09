package by.rakovets.interview.content_parser.generators;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.model.FileContent;

public interface DocumentationGenerator {
    String generate(FileContent fileComponents) throws ContentParserException;
}
