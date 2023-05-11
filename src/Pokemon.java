import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pokemon {
        public String name;
    int id;

    int growth_time;
    int max_harvest;
   int  natural_gift_power;
    int size;
    int smoothness;
   int  soil_dryness;
   String natural_gift_type;
   // private ArrayList<Abilitie> abilities;            // list
    //Map<String, Abilitie> abilities; // map
        ArrayList<Map> abilities;
    ArrayList<Abilitie> powers=new ArrayList<Abilitie>();;
    Map species;
    Pics sprites;
  //  Image pic;
    ArrayList<Image> pic = new ArrayList<Image>();



        public void print(){
            System.out.println("name = "+name);
            System.out.println("id = "+id);
            System.out.println("growth_time = "+growth_time);
            System.out.println("max+harves = "+max_harvest);
            System.out.println("natural_gift_power = "+natural_gift_power);
            System.out.println("smoothness = "+smoothness);
            System.out.println("soil_dryness = "+name);
            System.out.println("gifttyp = "+natural_gift_type);

            System.out.println(species.toString());
            System.out.println(sprites.back_default);
            URL url = null;
            try {
                url = new URL(sprites.back_default);

                pic.add(ImageIO.read(url));
                url = new URL(sprites.front_default);
                pic.add(ImageIO.read(url));
                url = new URL(sprites.front_female);
                pic.add(ImageIO.read(url));

                url = new URL(sprites.front_shiny);
                pic.add(ImageIO.read(url));

                url = new URL(sprites.front_shiny_female);
                pic.add(ImageIO.read(url));

                url = new URL(sprites.back_shiny_female);
                pic.add(ImageIO.read(url));


            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int x=0;x<abilities.size();x++) {
            System.out.println("this is a map"+ abilities.toString());
            double test= (double) abilities.get(x).get("slot");


            }

            for(int x=0;x<powers.size();x++){
                System.out.println("just to show you you can "+powers.get(x).abilitie);
            }

           // System.out.println(abilities.get(0).name);A

       // gsonParsJson(s);


        }
    public static void getstuff(String myurl) throws UnsupportedEncodingException {
        // Create a neat value object to hold the URL
        String www=myurl;
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
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr="fred";
        while (true) {
            try {
                if (!((inputStr = streamReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            responseStrBuilder.append(inputStr);
        }
        System.out.println(responseStrBuilder.toString());
        gsonParsJson(responseStrBuilder.toString());

    }
    public static void gsonParsJson(String text){
        System.out.println(text);
        JsonReader jsonReader = new JsonReader(new StringReader(text));

        Gson gson = new Gson();

        // 1. JSON file to Java object
        String result = gson.toJson(text);
        System.out.println("RESULTS!!!!"+result);


        Abilitie abilitie = gson.fromJson(jsonReader, Abilitie.class);

        // print staff object
        abilitie.print();
    }


}
