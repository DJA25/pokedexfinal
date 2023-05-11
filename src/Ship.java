import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Ship {

    private String name=null;
    private String model=null;
    private String gmanufacturer=null;
    private String cost= null;
    private double length=0.0;


    public void printsome(){
        System.out.println("name = "+name);
        System.out.println("model = "+model);
        System.out.println("manufacture = "+gmanufacturer);
        System.out.println("cost = "+cost);

        System.out.println("length = "+length);

    }


    //getters and setters
}