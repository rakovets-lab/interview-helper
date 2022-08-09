package by.rakovets.interview.content_parser.factory.elements.common;

public abstract class AbstractElement implements Element {
    protected String content;

    public AbstractElement(String content) {
        this.content = content;
    }
}
