package ir.smartpath.connection;

import org.apache.commons.lang3.StringUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

public class HttpConnection {
    public static void urlConnection(HashMap<String, String> header, String requestMethod, String url, String body  ) throws IOException {

        /*header.put("Content-Type", );*/
        Logger logger = Logger.getLogger(String.valueOf(HttpConnection.class));


        logger.info("checking inputs were null or not ");
        if (Objects.isNull(header) || StringUtils.isBlank(requestMethod) || StringUtils.isBlank(url) || Objects.isNull(body)) {
            throw new NullPointerException("It is null");
        }


        logger.info("creating a request the http url connection ");
        HttpURLConnection connection = null;

        logger.info("take a new url from function input");
        URL urll = new URL(url);

        logger.info("http connection");
        connection = (HttpURLConnection) urll.openConnection();

       /* logger.info("set content-type");
        connection.setRequestProperty("", );*/

        logger.info("set requestMethod");
        connection.setRequestMethod(requestMethod);

        logger.info("set header");
        for (String key : header.keySet()) {
            connection.setRequestProperty(key, header.get(key));

        }


        logger.info("set body");


        logger.info("connection is connected");
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
