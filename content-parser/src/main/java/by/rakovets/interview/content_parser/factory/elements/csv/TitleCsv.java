package by.rakovets.interview.content_parser.factory.elements.csv;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class TitleCsv extends AbstractElement {
    public TitleCsv(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        return "\"" + super.content.trim() + "\", ";
    }
}
