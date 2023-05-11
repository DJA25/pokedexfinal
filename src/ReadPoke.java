import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class ReadPoke extends JFrame implements Runnable,MouseMotionListener, KeyListener {

    private static Pokemon myPoke;
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    static ReadPoke r;
    public int charnum=0;
    public static void main(String[] args) {


        try {
            getstuff();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
         r= new ReadPoke();
        r.setUpGraphics();

        r.run();

    }
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
      //  g.fillRect(100,100,100,100);
        g.drawImage(ReadPoke.myPoke.pic.get(charnum),200,200,200,200,this);
        g.dispose();

        bufferStrategy.show();
    }
    public static void gsonParsJson(String text) {

        JsonReader jsonReader = new JsonReader(new StringReader(text));

        Gson gson = new Gson();

        // 1. JSON file to Java object
        String result = gson.toJson(text);
       // System.out.println("RESULTS!!!!" + result);



        Result pokelist = gson.fromJson(jsonReader, Result.class);

        // print staff object
        pokelist.print();

    }

    public static void getstuff() throws UnsupportedEncodingException {
        // API url
        String www = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=200";

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
        //System.out.println(inputStr);
        Gson gson = new Gson();

      //  Map map = gson.fromJson(inputStr, Map.class);
      //  System.out.println("ppp"+map.toString());
     //   System.out.println(map.get("results"));

        gsonParsJson(inputStr);

    }
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        canvas.addMouseMotionListener(this);
        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        charnum++;
        if(charnum>=ReadPoke.myPoke.pic.size()){
            charnum=0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void run() {
        while(true) {

            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }
    public void pause(int time ){
        //sleep
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {

        }
    }
    public static void setPoke(Pokemon p){
        myPoke=p;
    }
}