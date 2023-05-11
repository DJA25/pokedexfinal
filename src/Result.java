import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result {
    private ArrayList<CharicNameAPI> results;            // list
    public void print(){

        for(int x=0;x<results.size();x++) {
            System.out.println(results.get(x).name+"  "+results.get(x).url);

        }
System.out.println("****************");
        for (CharicNameAPI myresult : results)
        {
            if(myresult.name.equals("pikachu")){
               System.out.println("found PIKACHU!!!");
                System.out.println("test"+myresult.url);

                try {
                    getstuff(myresult.url);

                    //System.out.println("test");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    public static void getstuff(String myurl) throws UnsupportedEncodingException {
        // API url
        String www = myurl;

        URL url = null;
        try {
            url = new URL(www);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

// Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

// Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");


// This line makes the request
        InputStream responseStream = null;
        try {
            responseStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(responseStream, "UTF-8"));

        String inputStr = "fred";
        try {
            inputStr = streamReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gsonParsJson(inputStr);

    }

    public static void gsonParsJson(String text) {
       System.out.println("gsonParsJson  "+text);
        JsonReader jsonReader = new JsonReader(new StringReader(text));

        Gson gson = new Gson();

        // 1. JSON file to Java object
        String result = gson.toJson(text);
    //    System.out.println("RESULTS!!!!" + result);


        Pokemon hero = gson.fromJson(jsonReader, Pokemon.class);

        // print staff object
        hero.print();
        ReadPoke.setPoke(hero);
    }

}
