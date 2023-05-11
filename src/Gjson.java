import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Gjson {
    public static void main(String[] args) {


        try {
            getstuff();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public static void gsonParsJson(String text){
        System.out.println(text);
        JsonReader jsonReader = new JsonReader(new StringReader(text));

        Gson gson = new Gson();

        // 1. JSON file to Java object
          String result = gson.toJson(text);
          System.out.println("RESULTS!!!!"+result);


/*
        try {
            while(jsonReader.hasNext()){
                JsonToken nextToken = jsonReader.peek();
                System.out.println("-"+nextToken);
                if(JsonToken.BEGIN_ARRAY.equals(nextToken)){
                    jsonReader.beginArray();
                }
                if(JsonToken.BEGIN_OBJECT.equals(nextToken)){

                    jsonReader.beginObject();

                } else if(JsonToken.NAME.equals(nextToken)){

                    String name  =  jsonReader.nextName();
                    System.out.println("3333"+name);

                } else if(JsonToken.STRING.equals(nextToken)){

                    String value =  jsonReader.nextString();
                    System.out.println(value);

                } else if(JsonToken.NUMBER.equals(nextToken)){

                    long value =  jsonReader.nextLong();
                    System.out.println(value);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

 */
      //  JsonElement json = gson.fromJson(jsonReader, JsonElement.class);

     //   String jsonInString = gson.toJson(json);

       // System.out.println(jsonInString);
        Charicter starwarchar = gson.fromJson(jsonReader, Charicter.class);

        // print staff object
        starwarchar.printsome();
    }
    public static void getstuff() throws UnsupportedEncodingException {
        // API url
        String www="https://swapi.dev/api/people/4/";

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

        String inputStr="fred";
        try {
            inputStr = streamReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gsonParsJson(inputStr);

    }
}
