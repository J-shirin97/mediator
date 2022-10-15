package ir.smartpath;

import ir.smartpath.connection.HttpConnection;

import java.io.IOException;
import java.util.ArrayList;



public class Application {
    public static void main(String[] args) throws IOException {
        HttpConnection.urlConnection(new ArrayList<>(),"" , "localhost:3000/","");
    }
}
