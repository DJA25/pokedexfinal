import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CharicNameAPI {
    String name;
    String url;

    public void print(){
        System.out.println("name = "+name+ " url= "+url);
    }


}
