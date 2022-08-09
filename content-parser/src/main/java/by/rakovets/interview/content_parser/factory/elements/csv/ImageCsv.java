package by.rakovets.interview.content_parser.factory.elements.csv;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class ImageCsv extends AbstractElement {

    public ImageCsv(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        return super.content.trim();
    }

    public String getContent() {
        return super.content;
    }

    public void setContent(String newContent) {
        super.content = newContent;
    }
}
