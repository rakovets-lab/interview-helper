package by.rakovets.interview.content_parser.factory.elements.common;

import java.util.List;

public abstract class ListAbstractElement implements Element {
    protected List<String> list;

    protected ListAbstractElement(List<String> list) {
        this.list = list;
    }
}
