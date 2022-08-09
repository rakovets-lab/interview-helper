package by.rakovets.interview.content_parser.factory.elements.csv;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;

public class QuestionCsv extends AbstractElement {

    public QuestionCsv(String content) {
        super(content);
    }

    @Override
    public String getFormatedContent() {
        return "\"" + super.content.trim().replaceAll("([0-9])+.\\s", "")
                .replace("\n","").replace("&lt;", "<").
                replace("&gt;", ">").replace((" "), " ") + "\", ";
    }
}
