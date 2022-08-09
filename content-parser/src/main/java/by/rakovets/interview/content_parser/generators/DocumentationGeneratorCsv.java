package by.rakovets.interview.content_parser.generators;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.factory.elements.common.Element;
import by.rakovets.interview.content_parser.model.FileContent;

public class DocumentationGeneratorCsv implements DocumentationGenerator {
    @Override
    public String generate(FileContent fileComponents) throws ContentParserException {
        String beginningOfFile = "Тема, Вопрос, Ответ\n";
        String title = fileComponents.getTitle().getFormatedContent();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(beginningOfFile);
        fileComponents.getQuestionsAndAnswers().forEach((key, value) -> {
            if(value.size() > 0) {
                stringBuilder.append(title);
                stringBuilder.append(key.getFormatedContent());
                stringBuilder.append("\"");
                for (Element element : value) {
                    stringBuilder.append(element.getFormatedContent()).append(" ");
                }
                stringBuilder.append("\"\n");
            }
        });
        return stringBuilder.toString();
    }
}
