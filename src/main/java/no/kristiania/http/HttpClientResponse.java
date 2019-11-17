package no.kristiania.http;


import java.io.IOException;
import java.io.InputStream;

public class HttpClientResponse extends HttpMessage {

    public HttpClientResponse(InputStream inputStream)throws IOException {
        super(inputStream);
    }

}
