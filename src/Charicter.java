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

    public class Charicter {

        private String birth_year=null;
        private String eye_color=null;
        private String gender=null;
        private String haircolor;
        private int heightininch=0;
        private String homeworldapi=null;
        public int mass=0;
        public String name=null;
        public String created=null;
        public String edited=null;
        public String url=null;


        private String[] films;              // array
        private String[] species;              // array
        private String[] starships;              // array
        private String[] vehicles;


        private List<String> skills;            // list
        private Map<String, BigDecimal> salary; // map

        public void printsome(){
            System.out.println("name = "+name);
            System.out.println("height = "+heightininch);
            System.out.println("eye = "+eye_color);
            System.out.println("homeworld = "+homeworldapi);
            for (String var : starships)   {
                System.out.println("ship = "+var);
                var= "https"+var.substring(4,var.length());
                System.out.println("!!!!"+var);
                try {
                    getstuff(var);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("gender = "+gender);

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


            Ship myship = gson.fromJson(jsonReader, Ship.class);

            // print staff object
            myship.printsome();
        }


        //getters and setters
    }









