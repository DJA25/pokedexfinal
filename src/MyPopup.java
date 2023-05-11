import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class MyPopup{

    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public JButton pressme;

    public static void main(String[] args){
            MyPopup my = new MyPopup();
            my.setUpGraphics();
    }
    private  void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        pressme= new JButton("PUSH ME");
        frame.add(pressme);


        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

       // frame.add(canvas);  // adds the canvas to the panel.




        // frame operations
        frame.setSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel

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

}
