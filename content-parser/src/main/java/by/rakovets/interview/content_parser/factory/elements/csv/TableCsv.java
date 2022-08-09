package by.rakovets.interview.content_parser.factory.elements.csv;

import by.rakovets.interview.content_parser.factory.elements.common.TableAbstractElement;

import java.util.List;

public class TableCsv extends TableAbstractElement {
    public TableCsv(List<List<String>> table, String caption) {
        super(table, caption);
    }

    @Override
    public String getFormatedContent() {
        StringBuilder formatedContent = new StringBuilder();
        StringBuilder line;
        formatedContent.append(super.caption);
        for(List<String> list : table) {
            line = new StringBuilder();
            for(String cell : list) {
                line.append(cell.trim().replace("\n","").replace("&lt;", "<").
                        replace("&gt;", ">").replace((" "), " "));
            }
            formatedContent.append(line);
        }
        return formatedContent.toString();
    }
}
