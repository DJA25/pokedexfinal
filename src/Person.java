import java.io.Serializable;

public class Person implements Serializable {
    public String first,last;
    public Person(String fir,String las){

        first=fir;
        last=las;
    }
    public void print(){
        System.out.println("this is the person "+first+last);
    }
}
