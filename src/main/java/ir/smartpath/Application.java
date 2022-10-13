package ir.smartpath;

import java.io.IOException;
import java.util.ArrayList;



public class Application {
    public static void main(String[] args) throws IOException {
        HttpConnection.urlConnection(new ArrayList<>(),"GET" , "localhost:3000/","");
    }
}
