package by.rakovets.interview.content_parser.model;

public enum Tag {
    P("p"), H4("h4"), H3("h3"), A("a"), UL("ul"), LI("li"), TABLE("table"), CAPTION("caption"), SPAN("span"),
    THEAD("thead"), TBODY("tbody"), TFOOT("tfoot"), TR("tr"), TH("th"), TD("td"), DIV("div");
    private String tag;

    Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
