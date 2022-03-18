import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            Dialogue.showMenu(); // Input key, origFileName, encriptFileName
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}