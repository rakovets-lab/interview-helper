package by.rakovets.interview.content_parser.factory.elements.csv;

import by.rakovets.interview.content_parser.factory.elements.common.ListAbstractElement;

import java.util.List;

public class OneLevelListCsv extends ListAbstractElement {

    public OneLevelListCsv(List<String> list) {
        super(list);
    }

    @Override
    public String getFormatedContent() {
        StringBuilder stringBuilder = new StringBuilder();
        String lineStart = "* ";
        String lineEnd = " ";
        for(String element : super.list) {
            String formatedElement = element.replace("\n","").replace("&lt;", "<").
                    replace("&gt;", ">").replace((" "), " ");
            stringBuilder.append(lineStart).append(formatedElement).append(lineEnd);
        }
        return stringBuilder.toString();
    }
}
