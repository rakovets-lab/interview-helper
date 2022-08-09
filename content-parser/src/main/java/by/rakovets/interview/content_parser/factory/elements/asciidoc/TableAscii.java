package by.rakovets.interview.content_parser.factory.elements.asciidoc;

import by.rakovets.interview.content_parser.factory.elements.common.TableAbstractElement;
import java.util.List;

public class TableAscii extends TableAbstractElement {

    public TableAscii(List<List<String>> table, String caption) {
        super(table, caption);
    }

    @Override
    public String getFormatedContent() {
        List<List<String>> tb = super.table;
        final String stepForward = "\n\n";
        final String tableCaption = "." + caption + "\n";
        final String starOfTheTable = "|===\n\n";
        final String newLine = "\n";
        final String starOfNormalCell = "|";
        final String endOfTheTable = "\n|===";
        StringBuilder sb = new StringBuilder();
        sb.append(stepForward);
        if (!caption.equals("")) {
            sb.append(tableCaption);
        }
        sb.append(starOfTheTable);

        for (int i = 1; i < tb.size(); i++) {
            if (tb.get(i).size() < tb.get(i - 1).size()) {
                tb.get(i).add(0, "");
            }
        }

        for (List<String> list : tb) {
            for (String element : list) {
                sb.append(starOfNormalCell);
                sb.append(element);
            }
            sb.append(newLine);
        }
        sb.append(endOfTheTable);
        return sb.toString();
    }
}
