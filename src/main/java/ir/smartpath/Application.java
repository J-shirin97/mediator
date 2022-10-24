package ir.smartpath;

import ir.smartpath.connection.HttpConnection;

import java.io.IOException;
import java.util.HashMap;


public class Application {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("b", "m");
        myMap.put("v", "c");
        String test = "<note>\n" +
                "<to>Tove</to>\n" +
                "<from>Jani</from>\n" +
                "<heading>Reminder</heading>\n" +
                "<body>Don't forget me this weekend!</body>\n" +
                "</note>";

        HttpConnection.urlConnection(
                myMap,
                "POST",
                "http://localhost:3000",
                test,
                "application/xml",
                "headers.host");
    }
}
