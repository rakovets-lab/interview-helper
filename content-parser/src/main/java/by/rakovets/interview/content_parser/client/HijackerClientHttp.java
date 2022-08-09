package by.rakovets.interview.content_parser.client;

import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.exception.ContentParserException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HijackerClientHttp implements HijackerClient {
    private final HttpClient httpClient;

    public HijackerClientHttp() {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public String getContentByUri(SiteContentDto siteContentDto) throws ContentParserException {

        URI uri;
        try {
            uri = new URI(siteContentDto.getStringUri());
        } catch (URISyntaxException e) {
            throw new ContentParserException("Wrong URI!", e);
        }
        siteContentDto.setUri(uri);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        try {
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            throw new ContentParserException("Problem with Internet Connection!", e);
        } catch (InterruptedException e) {
            throw new ContentParserException("Internal Problem", e);
        }
    }
}
