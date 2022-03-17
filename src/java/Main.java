import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            Dialogue.menu(); // Input key, origFileName, encriptFileName
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}