package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.ListAbstractElement;

import java.util.List;

public class OneLevelListAscii extends ListAbstractElement {
    public OneLevelListAscii(List<String> list) {
        super(list);
    }

    @Override
    public String getFormatedContent() {
        String startOfTheList = "\n";
        String newLine = "\n* ";
        StringBuilder sb = new StringBuilder();
        sb.append(startOfTheList);
        for (String element : super.list) {
            sb.append(newLine);
            sb.append(element);
        }
        return sb.toString();
    }
}
