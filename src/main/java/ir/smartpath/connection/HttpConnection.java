package ir.smartpath.connection;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class HttpConnection {
    public static void urlConnection(List<String> header, String requestMethod, String url, String body) throws IOException {

        //log in console
        Logger logger = Logger.getLogger(String.valueOf(HttpConnection.class));
        /*logger.info("");*/

        logger.info("checking inputs were null or not ");
        if (Objects.isNull(header) || StringUtils.isBlank(requestMethod) || StringUtils.isBlank(url) || StringUtils.isBlank(body)){
            throw new NullPointerException("It is null");
        }


        // creating a request the http url connection

        logger.info("creating a request the http url connection ");
        HttpURLConnection connection = null;
        URL url1 = new URL(url);
        connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod(connection.getRequestMethod());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();


        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content);

    }



}
