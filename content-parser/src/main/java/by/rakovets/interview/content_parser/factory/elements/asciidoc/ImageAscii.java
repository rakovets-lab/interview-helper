package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class ImageAscii extends AbstractElement {

    public ImageAscii(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        String start = "\n\nimage::";
        String end = "[" + "]";
        return start + super.content + end;
    }

    public String getContent() {
        return super.content;
    }

    public void setContent(String newContent) {
        super.content = newContent;
    }
}
