package no.kristiania.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {
//tester
    private static ServerSocket serverSocket;
    private String fileLocation = null;

    public HttpServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer(80);
        httpServer.setFileLocation("src/main/resources/index.html");
        System.out.println(httpServer.getPort());
        httpServer.start();
    }

    void start(){
        new Thread(this::run).start();
    }

    private void run(){
        try {
            while(true) {
                Socket socket = serverSocket.accept();


                HttpServerRequest request = new HttpServerRequest(socket.getInputStream());
                String requestLine = request.getStartLine();

                String requestTarget = requestLine.split(" ")[1];


                    int questionPos = requestTarget.indexOf('?');
                    String requestPath = questionPos == -1 ? requestTarget : requestTarget.substring(0, questionPos);
                    if (!requestPath.equals("/echo")) {
                        File file = new File(fileLocation + requestPath);

                        socket.getOutputStream().write(("http/1.1 200 OK\r\n" +
                                "content-length: " + file.length() + "\r\n" +
                                "content-type: html" +
                                "Connection: close\r\n" +
                                "\r\n").getBytes());

                        new FileInputStream(file).transferTo(socket.getOutputStream());
                        return;

                    }

                    Map<String, String> requestParameters = parseRequestParameters(requestTarget);

                    String statusCode = requestParameters.getOrDefault("status", "200");
                    String location = requestParameters.get("location");
                    String body = requestParameters.getOrDefault("body", "Hello World!");


                    socket.getOutputStream().write(("http/1.0 " + statusCode + " OK\r\n" +
                            "content-length: " + body.length() + "\r\n" +
                            (location != null ? "location: " + location + "\r\n" : "") +
                            "\r\n" +
                            body).getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> parseRequestParameters(String requestTarget) {
        Map<String, String> requestParameters = new HashMap<>();
        int questionPos = requestTarget.indexOf('?');
        if(questionPos != -1) {
            String query = requestTarget.substring(questionPos+1);
            for (String parameter : query.split("&")) {
                int equalsPos = parameter.indexOf('=');
                String parameterValue = parameter.substring(equalsPos+1);
                String parameterName = parameter.substring(0, equalsPos);
                requestParameters.put(parameterName, parameterValue);

            }
        }
        return requestParameters;
    }

    public int getPort() {
       return serverSocket.getLocalPort();
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
