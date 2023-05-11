import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeSet;
//import SwingX.AutoCompleteDecorator;
//import javax.swing.
// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;

// Program for print data in JSON format.
public class ReadJson {
    static JPanel lastSplit;
    static JFrame mf;
    static String imageLabel;
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject all = pull("https://pokeapi.co/api/v2/pokemon?limit=151&offset=0");
        JSONArray arr = (JSONArray) all.get("results");

        TreeSet<String> names = new TreeSet<>();

        for(Object o: arr) {
            JSONObject o2 = (JSONObject) o;
            String name = o2.get("name").toString();
            names.add(name);
        }
//            for(String s: names) System.out.println(s);


        mf = new JFrame("Pokedex");
        mf.setSize(1000, 800);
        mf.setLayout(new BorderLayout());
        JPanel search = new JPanel(new BorderLayout());
        search.add(new JLabel(" Search For Pokemon: "), BorderLayout.WEST);
        JTextField searchField = new JTextField();
        search.add(searchField, BorderLayout.CENTER);
        JPanel plusgo = new JPanel(new GridLayout(2,1));
        plusgo.add(search);
        JButton go = new JButton("Go!");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!names.contains(searchField.getText().toLowerCase())) {
                    searchField.setText("Could Not Find Pokemon. Try Again and Check Your Spelling Or Choose a Pokemon Below.");
                }
                else {
                    run(searchField.getText().toLowerCase());
                }
            }
        });
        plusgo.add(go);
        mf.add(plusgo, BorderLayout.NORTH);
        JPanel splitter = new JPanel(new GridLayout(2, 1));
        JPanel grid = new JPanel(new GridLayout(26, 6));
        int count = 0;
        for(String s: names) {
            JButton button = new JButton(s);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    run(button.getText());
                }
            });
            grid.add(button);
        }
        JScrollPane gg = new JScrollPane(grid);
//        gg.add(grid);
        splitter.add(gg);
        JPanel bottom = new JPanel(new BorderLayout());
//            JLabel pokena,me = new JLabel("ADD SELECTED POKEMON NAME HERE", SwingConstants.CENTER);
//            bottom.add(pokename, BorderLayout.NORTH);
        lastSplit = new JPanel(new GridLayout(1, 2));
//            lastSplit.add(new JLabel("PHOTO HERE", SwingConstants.CENTER));
//            lastSplit.add(new JLabel("DATA HERE", SwingConstants.CENTER));

        bottom.add(lastSplit);
        lastSplit.add(new JLabel("hola"));
        splitter.add(bottom);
        mf.add(splitter, BorderLayout.CENTER);
        mf.setVisible(true);

//            https://pokeapi.co/api/v2/pokemon


//            JSONObject file = new JSONObject();
//            file.put("Full Name", "Ritu Sharma");
//            file.put("Roll No.", new Integer(1704310046));
//            file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
//            System.out.print(file.get("Tution Fees"));

//            JComboBox comboBox = new JComboBox();
//            AutoCompleteDecorator.decorate(comboBox);
    }
    public static void run(String name) {
        try {
            JSONObject p = pull("https://pokeapi.co/api/v2/pokemon/" + name);
            String img = pullImg(p);
            URL url = new URL(img);
            BufferedImage c = ImageIO.read(url);
            Image c2 = c.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(c2);
            lastSplit.removeAll();

            JPanel info = new JPanel(new GridLayout(4, 1));
            JLabel n = new JLabel("Name: " + name, SwingConstants.CENTER);
            n.setFont(new Font("Serif", Font.PLAIN, 30));
            info.add(n);
            JSONArray arr = ((JSONArray)p.get("types"));
            String type = ((JSONObject)((JSONObject)(arr.get(0))).get("type")).get("name").toString();
//                for(Object o: arr) {
//
//                }
            JLabel t= new JLabel("Type: " + type, SwingConstants.CENTER);
            t.setFont(new Font("Serif", Font.PLAIN, 30));
            info.add(t);
            JLabel a =  new JLabel("Abilities:", SwingConstants.CENTER);
            a.setFont(new Font("Serif", Font.PLAIN, 30));
            info.add(a);

//                JPanel info2 = new JPanel(new BorderLayout());
//                info2.add(info, BorderLayout.CENTER);
            JSONArray abs = (JSONArray) p.get("abilities");
//                String text = "";
            int count = abs.size();
            JPanel var = new JPanel(new GridLayout(count, 1));
            for(Object o: abs) {

//                    count++;
                JSONObject o2 = (JSONObject) o;
                o2 = (JSONObject) o2.get("ability");

                String text=o2.get("name").toString();
                JLabel ability = new JLabel(text, SwingConstants.CENTER);
                ability.setFont(new Font("Serif", Font.PLAIN, 23));
                var.add(ability);
            }

            info.add(var);

            lastSplit.add(info);
            lastSplit.add(new JLabel(image));

//                lastSplit.add()
            mf.setVisible(true);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static JSONObject pull(String URL) throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {

            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        return jsonObject;
    }

    //            System.out.println(jsonObject);
    public static String pullImg(org.json.simple.JSONObject jsonObject) {
        String imageLabel = ((org.json.simple.JSONObject) jsonObject.get("sprites")).get("front_default").toString();
        return imageLabel;
    }




}

