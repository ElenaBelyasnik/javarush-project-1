import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EncryptFile {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static char[] encrAlphabet = new char[ALPHABET.length];
/* !!! раскомментировать в конце разработки
    static int key = 0;
    static String origFileName = "";
    static String encrFileName = "";
*/
    static int key = 3;
    static String origFileName = "C:\\ELENA\\JR2\\Elena_Belyasnik_JavaRush_Project_1\\OrigFile\\orig.txt";
    static String encrFileName = "C:\\ELENA\\JR2\\Elena_Belyasnik_JavaRush_Project_1\\EncryptFile\\encrypt.txt";
    static File origFile = null;
    static File encrFile = null;

    //** Init start params: origFile, encriptFile...
    public static void initParam() {
        if (key > ALPHABET.length) key = ALPHABET.length;
        EncryptFile.origFile = new File(EncryptFile.origFileName);
        EncryptFile.encrFile = new File(EncryptFile.encrFileName);
        int length = encrAlphabet.length;
        for (int i = 0; i < length; i++) {
            int delta = i + key;
            if (delta < length ) {
                encrAlphabet[i] = ALPHABET[delta];
            } else {
                encrAlphabet[i] = ALPHABET[i + key - length];
            }
        }
        System.out.println(Arrays.toString(ALPHABET));
        System.out.println(Arrays.toString(encrAlphabet));
    }

    //** Encrypt File
    public static void encrypt() {

    }




}
