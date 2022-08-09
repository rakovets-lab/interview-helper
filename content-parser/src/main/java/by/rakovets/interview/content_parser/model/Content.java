package by.rakovets.interview.content_parser.model;

public class Content {
    protected String fileName;
    protected String pathForSaving;
    protected String content;

    public Content(String fileName, String pathForSaving, String content) {
        this.fileName = fileName;
        this.pathForSaving = pathForSaving;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathForSaving() {
        return pathForSaving;
    }

    public void setPathForSaving(String pathForSaving) {
        this.pathForSaving = pathForSaving;
    }
}
