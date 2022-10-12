package ir.smartpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class HttpConnection {
    public static void urlConnection() throws IOException {

        //log in console
        Logger logger = Logger.getLogger(String.valueOf(HttpConnection.class));

        // creating a request the http url connection
        HttpURLConnection connection = null;

        URL url = new URL("https://localhost:3000/");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(connection.getRequestMethod());
        /*connection.setRequestMethod("GET");*/
        connection.setRequestProperty("Content-Type" , "application/json");
        connection.connect();


        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine ;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();



    }
}
