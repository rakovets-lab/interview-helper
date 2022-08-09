package by.rakovets.interview.content_parser.factory.elements.common;

import java.util.List;

public abstract class TableAbstractElement implements Element {

    protected List<List<String>> table;
    protected String caption;

    protected TableAbstractElement(List<List<String>> table, String caption) {
        this.table = table;
        this.caption = caption;
    }

}
