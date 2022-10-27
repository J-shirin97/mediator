package ir.smartpath.connection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import ir.smartpath.cache.CacheHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class HttpConnection {
    public HttpConnection() {
    }

    public static void urlConnection(HashMap<String, String> header, String requestMethod, String url, String body, String contentType, String path) throws IOException {



        Logger logger = Logger.getLogger(String.valueOf(HttpConnection.class));


        logger.info("checking inputs were null or not ");
        if (Objects.isNull(header) || StringUtils.isBlank(requestMethod) || StringUtils.isBlank(url) || Objects.isNull(body) || Objects.isNull(contentType)) {
            throw new NullPointerException("It is null");
        }


        CacheHandler.getElement(path);
        HttpURLConnection connection = getConnection(header, requestMethod, url, body, contentType, logger);


        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();


        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        //convert string to json

        String json = content.toString();
        JsonObject convertJson = new Gson().fromJson(json, JsonObject.class);

        //dynamic log for response
        convertToObject(path, logger, convertJson);

        in.close();
        System.out.println(content);

    }

    public static HttpURLConnection getConnection(HashMap<String, String> header, String requestMethod, String url, String body, String contentType, Logger logger) throws IOException {
        logger.info("creating a request the http url connection ");
        HttpURLConnection connection = null;

        logger.info("take a new url from function input");
        URL urll = new URL(url);

        logger.info("http connection");
        connection = (HttpURLConnection) urll.openConnection();

        logger.info("content-type : ");
        connection.setRequestProperty("content-type", contentType);


        logger.info("requestMethod : ");
        connection.setRequestMethod(requestMethod);

        logger.info("header : ");
        for (String key : header.keySet()) {
            connection.setRequestProperty(key, header.get(key));
        }


        logger.info("body : ");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setDoOutput(true);
        OutputStream connectionOutputStream = connection.getOutputStream();
        connectionOutputStream.write(body.getBytes());
        connectionOutputStream.flush();
        connectionOutputStream.close();

        logger.info("connection is connected");
        connection.connect();

        return connection;
    }


    public static void convertToObject(String path, Logger logger, JsonObject convertJson) {
        List<String> splitString = Arrays.asList(path.split("\\."));
        if (splitString.size() > 1) {

            Object object = convertJson;
            for (String stringObj : splitString) {
                if (object instanceof JsonObject) {
                    JsonObject jsonObject = (JsonObject) object;
                    object = jsonObject.get(stringObj);
                }
                if (object instanceof JsonPrimitive) {
                    JsonPrimitive jsonPrimitive = (JsonPrimitive) object;
                    System.out.println(jsonPrimitive.getAsString());
                    logger.info("log : " + jsonPrimitive.getAsString());

                    CacheHandler.ehcacheManager(stringObj, String.valueOf(jsonPrimitive));

                }
            }
        }
    }
}



