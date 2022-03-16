import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class EncryptFile {
    static String origFileName = "";
    static String encrFileName = "";
    static File origFile = null;
    static File encrFile = null;
    static int key = 0;



    //** Input key, origFileName, encriptFileName
    public static void inputParam() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите ключ шифрования: ");
            key = Integer.parseInt(reader.readLine());
            // C:\ELENA\JR2\Elena_Belyasnik_JavaRush_Project_1\OrigFile\orig.txt
            System.out.println("Введите путь к файлу, который нужно зашифровать: ");
            origFileName = reader.readLine();
            origFile = new File(origFileName);
            // C:\ELENA\JR2\Elena_Belyasnik_JavaRush_Project_1\EncryptFile\encrypt.txt
            System.out.println("Введите путь к файлу, в котором будет зашифрованный текст: ");
            encrFileName = reader.readLine();
            encrFile = new File(encrFileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
