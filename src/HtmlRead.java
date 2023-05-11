import java.net.*;
import java.io.*;

public class HtmlRead {

    public HtmlRead(){

        try{
            System.out.println();
            URL url = new URL("https://espn.com/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }
            reader.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }



}
