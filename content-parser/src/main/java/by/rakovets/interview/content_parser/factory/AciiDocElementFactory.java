package by.rakovets.interview.content_parser.factory;

import by.rakovets.interview.content_parser.factory.elements.asciidoc.*;
import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.ListAbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.TableAbstractElement;

import java.util.List;

public class AciiDocElementFactory implements AbstractElementFactory {
    @Override
    public AbstractElement createText(String content) {
        return new TextAscii(content);
    }

    @Override
    public AbstractElement createQuestion(String content) {
        return new QuestionAscii(content);
    }

    @Override
    public AbstractElement createCode(String content) {
        return new CodeAscii(content);
    }

    @Override
    public TableAbstractElement createTable(List<List<String>> table, String caption) {
        return new TableAscii(table, caption);
    }

    @Override
    public ListAbstractElement createList(List<String> list) {
        return new OneLevelListAscii(list);
    }

    @Override
    public AbstractElement createImage(String content) {
        return new ImageAscii(content);
    }

    @Override
    public AbstractElement createTitle(String title) {
        return  new TitleAscii(title);
    }
}
