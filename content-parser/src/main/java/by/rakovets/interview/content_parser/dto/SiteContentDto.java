package by.rakovets.interview.content_parser.dto;

import java.net.URI;

public class SiteContentDto {
    private final String stringUri;
    private final String xPathForQuestions;
    private  URI uri;

    public SiteContentDto(String uri, String xPathForQuestions) {
        this.stringUri = uri;
        this.xPathForQuestions = xPathForQuestions;
    }

    public String getStringUri() {
        return stringUri;
    }

    public String getxPathForQuestions() {
        return xPathForQuestions;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
