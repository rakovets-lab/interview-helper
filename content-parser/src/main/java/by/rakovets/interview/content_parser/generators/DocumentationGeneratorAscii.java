package by.rakovets.interview.content_parser.generators;

import by.rakovets.interview.content_parser.factory.elements.common.Element;
import by.rakovets.interview.content_parser.model.FileContent;

public class DocumentationGeneratorAscii implements DocumentationGenerator {

    @Override
    public String generate(FileContent fileComponents) {
        StringBuilder sb = new StringBuilder();
        String start = fileComponents.getTitle().getFormatedContent();
        sb.append(start);
        if(fileComponents.getImageDir() != null) {
            String actualImageDir = "\n:imagesdir: " + fileComponents.getImageDir();
            sb.append(actualImageDir);
        }
        fileComponents.getQuestionsAndAnswers().forEach((key, value) -> {
            if(value.size() > 0) {
                sb.append(key.getFormatedContent());
                for (Element element : value) {
                    sb.append(element.getFormatedContent());
                }
            }
        });
        return sb.toString();
    }

}
