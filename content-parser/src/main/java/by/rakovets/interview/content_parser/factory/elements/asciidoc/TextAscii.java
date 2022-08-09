package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class TextAscii extends AbstractElement {
    public TextAscii(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        String formatedContent = "";
        if(!super.content.equals("") && !super.content.equals("\"") && !super.content.equals(" ")) {
            formatedContent = super.content.replace("\n", "");
            formatedContent = formatedContent.replace("\t","");
        }
        return "\n\n" + formatedContent;
    }
}
