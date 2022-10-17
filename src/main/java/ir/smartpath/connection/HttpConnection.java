package ir.smartpath.connection;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class HttpConnection {
    public static void urlConnection(HashMap<String, String> header, String requestMethod, String url, String body, String contentType) throws IOException {


        Logger logger = Logger.getLogger(String.valueOf(HttpConnection.class));


        logger.info("checking inputs were null or not ");
        if (Objects.isNull(header) || StringUtils.isBlank(requestMethod) || StringUtils.isBlank(url) || Objects.isNull(body) || Objects.isNull(contentType)) {
            throw new NullPointerException("It is null");
        }


        logger.info("creating a request the http url connection ");
        HttpURLConnection connection = null;

        logger.info("take a new url from function input");
        URL urll = new URL(url);

        logger.info("http connection");
        connection = (HttpURLConnection) urll.openConnection();

        logger.info("set content-type");
        connection.setRequestProperty("content-type", contentType);


        logger.info("set requestMethod");
        connection.setRequestMethod(requestMethod);


        logger.info("set header");
        for (String key : header.keySet()) {
            connection.setRequestProperty(key, header.get(key));
        }


        logger.info("set body");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setDoOutput(true);
        OutputStream connectionOutputStream = connection.getOutputStream();
        connectionOutputStream.write(body.getBytes());
        connectionOutputStream.flush();
        connectionOutputStream.close();


        logger.info("connection is connected");
        connection.connect();


        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }



        /*logger.info("log details :" + content);*/


        in.close();
        System.out.println(content);

    }


}
