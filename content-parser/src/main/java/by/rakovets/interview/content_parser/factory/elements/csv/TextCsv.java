package by.rakovets.interview.content_parser.factory.elements.csv;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class TextCsv extends AbstractElement {

    public TextCsv(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        return super.content.trim().replace("\n","").replace("&lt;", "<").
                replace("&gt;", ">").replace((" "), " ");
    }
}
