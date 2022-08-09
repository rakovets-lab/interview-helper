package by.rakovets.interview.content_parser.factory;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.ListAbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.TableAbstractElement;
import by.rakovets.interview.content_parser.factory.elements.csv.*;

import java.util.List;

public class CsvElementFactory implements AbstractElementFactory {
    @Override
    public AbstractElement createText(String content) {
        return new TextCsv(content);
    }

    @Override
    public AbstractElement createQuestion(String content) {
        return new QuestionCsv(content);
    }

    @Override
    public AbstractElement createCode(String content) {
        return new CodeCsv(content);
    }

    @Override
    public TableAbstractElement createTable(List<List<String>> table, String caption) {
        return new TableCsv(table, caption);
    }

    @Override
    public ListAbstractElement createList(List<String> list) {
        return new OneLevelListCsv(list);
    }

    @Override
    public AbstractElement createImage(String content) {
        return new CodeCsv(content);
    }

    @Override
    public AbstractElement createTitle(String title) {
        return new TitleCsv(title);
    }
}

