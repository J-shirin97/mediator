package ir.smartpath.connection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import ir.smartpath.cache.CacheHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static ir.smartpath.cache.CacheHandler.logger;
import static org.springframework.http.HttpHeaders.USER_AGENT;

public class HttpConnection {

    //http connection config
    public static void urlConnection(HashMap<String, String> header,
                                     String requestMethod,
                                     String url,
                                     String body,
                                     String contentType,
                                     String path,
                                     String expirePath,
                                     boolean flag,
                                     String userName)
            throws IOException {

        Logger logger = Logger.getLogger(String.valueOf(HttpConnection.class));

        //checking inputs
        logger.info("checking inputs were null or not ");
        if (Objects.isNull(header) ||
                StringUtils.isBlank(requestMethod) ||
                StringUtils.isBlank(url) ||
                Objects.isNull(body) ||
                Objects.isNull(contentType)) {
            throw new NullPointerException("It is null");
        }

        //caching response & userName
        CacheHandler.getElement(path + "//" + userName);

        HttpURLConnection connection = getConnection(header,
                requestMethod,
                url,
                body,
                contentType,
                logger,
                userName);

        // build stringBuilder for inputs
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = input.readLine()) != null) {
            content.append(inputLine);
        }
        input.close();


        //convert string to json

        String json = content.toString();
        JsonObject convertJson = new Gson().fromJson(json, JsonObject.class);

        //closing connection

        //expire time and caching
        String cache = convertToObject(path, convertJson);
        String time = convertToObject(expirePath, convertJson);

        long expiresTime = 0;
        if (flag) {
            expiresTime = Long.parseLong(time) - Instant.now().toEpochMilli();
        } else {
            expiresTime = Long.parseLong(time);
        }

        CacheHandler.putElement(path + "//" + userName, cache, expiresTime);

    }

    //http connection handling and log
    public static HttpURLConnection getConnection(HashMap<String, String> header,
                                                  String requestMethod,
                                                  String url,
                                                  String body,
                                                  String contentType,
                                                  Logger logger, String userName)
            throws IOException {



        logger.debug("creating a request the http url connection");
        HttpURLConnection connection = null;


        logger.debug("take a url");
        URL urll = new URL(url);

        logger.debug("http connection");
        connection = (HttpURLConnection) urll.openConnection();

        logger.debug("content-type : ");
        connection.setRequestProperty("content-type", contentType);


        logger.debug("requestMethod : ");
        connection.setRequestMethod("requestMethod" + requestMethod);

        logger.debug("header : ");
        for (String key : header.keySet()) {
            connection.setRequestProperty(key, header.get(key));
        }

        logger.debug("body : ");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        connection.setDoOutput(true);

        //Returns a String representation of this URL connection.
        OutputStream connectionOutputStream = connection.getOutputStream();

        connectionOutputStream.write(body.getBytes());

        //Closes this output stream and releases any system resources
        connectionOutputStream.flush();

        connectionOutputStream.close();


        logger.debug("connection is connected");
        connection.connect();
        return connection;

    }

    //create object from inputs string
    public static String convertToObject(String path, JsonObject convertJson) {

        List<String> splitString = Arrays.asList(path.split("\\."));
        if (splitString.size() >= 1) {

            Object object = convertJson;
            for (String stringObj : splitString) {
                if (object instanceof JsonObject) {
                    JsonObject jsonObject = (JsonObject) object;
                    object = jsonObject.get(stringObj);
                }
                if (object instanceof JsonPrimitive) {
                    JsonPrimitive jsonPrimitive = (JsonPrimitive) object;

                    logger.info("split response : " + jsonPrimitive.getAsString());

                    return jsonPrimitive.getAsString();
                }
            }
        }
        return StringUtils.EMPTY;
    }
}



