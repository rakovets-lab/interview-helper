package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class QuestionAscii extends AbstractElement {
    public QuestionAscii(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        String formatedContent = super.content.replace("\n\n","");
        return "\n\n== " + formatedContent;
    }
}
