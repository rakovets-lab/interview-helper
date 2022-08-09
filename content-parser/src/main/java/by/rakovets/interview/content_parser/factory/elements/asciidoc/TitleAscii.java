package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class TitleAscii extends AbstractElement {
    public TitleAscii(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        String formatedContent = super.content.replace("\n","");
        return "= " + formatedContent;
    }
}
