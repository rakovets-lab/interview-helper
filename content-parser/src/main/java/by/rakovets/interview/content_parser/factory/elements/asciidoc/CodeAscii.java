package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class CodeAscii extends AbstractElement {
    public CodeAscii(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        String start = "\n\n[source, java]\n----\n";
        String end = "\n----";
        String formatedContent = "";
        if (!super.content.equals("")) {
            formatedContent = start + super.content + end;
            formatedContent = formatedContent.replace("&lt;", "<");
            formatedContent = formatedContent.replace("&gt;", ">");
            formatedContent = formatedContent.replace((" "), " ");

        }
        return formatedContent;
    }
}
