package by.rakovets.interview.content_parser.factory;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.ListAbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.TableAbstractElement;

import java.util.List;

public interface AbstractElementFactory {
    AbstractElement createText(String content);

    AbstractElement createQuestion(String content);

    AbstractElement createCode(String content);

    TableAbstractElement createTable(List<List<String>> table, String caption);

    ListAbstractElement createList(List<String> list);

    AbstractElement createImage(String content);

    AbstractElement createTitle(String title);

}
