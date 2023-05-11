import java.io.*;
import java.util.Scanner;

// this was adapted from w3 school

public class savetest {
    String data;
    Person testPerson;
    Person loadPerson;
    public static void main(String[] args) {
       // FindInText find = new FindInText();  // first exampl
       // HtmlRead htm= new HtmlRead();  // second example

        savetest saveme= new savetest(); // third example
        saveme.write();           // third example
        saveme.read();            // third example

      //  saveme.writeSerialized();     // fourth example
      //  saveme.readSerialized();      // fourth example

        String hold= "this, is,a,trest,only,a,test";
        String[] s= hold.split("only");
        for(int x=0;x<s.length;x++){
            System.out.println(s[x]);
        }

    }


    public void write() {
        testPerson= new Person("Chris","Hales");
        try {
            FileWriter myWriter = new FileWriter("newfiletest.txt");
            myWriter.write("first="+testPerson.first);
            myWriter.write(",");
            myWriter.write("last="+testPerson.last);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void read(){
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("start!!"+data);
            }
            String[] list= data.split(",");
            for(String one:list){
                System.out.println(one);
            }
            loadPerson= new Person(list[0],list[1]);
            loadPerson.print();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeSerialized(){
        testPerson= new Person("Michele","Olandipo");

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("/tmp/student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(testPerson);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /tmp/student.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public void readSerialized(){
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadPerson = (Person) in.readObject();
            loadPerson.print();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

    }

}

