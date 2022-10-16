package ir.smartpath;

import ir.smartpath.connection.HttpConnection;


import java.io.IOException;
import java.util.HashMap;



public class Application {

    public static void main(String[] args) throws IOException {



        HttpConnection.urlConnection(
                new HashMap<>(),
                "",
                "http://localhost:3000",
                "");
    }
}
