package by.rakovets.interview.content_parser.model;

import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.Element;

import java.util.List;
import java.util.Map;

public class FileContent {
    private final Map<AbstractElement, List<Element>> questionsAndAnswers;
    private final AbstractElement title;
    private String imageDir;

    public FileContent(Map<AbstractElement, List<Element>> questionsAndAnswers, AbstractElement title) {
        this.questionsAndAnswers = questionsAndAnswers;
        this.title = title;
    }

    public Map<AbstractElement, List<Element>> getQuestionsAndAnswers() {
        return questionsAndAnswers;
    }

    public AbstractElement getTitle() {
        return title;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public String getImageDir() {
        return imageDir;
    }
}
