import java.io.*;
import java.util.ArrayList;

public class BrutForce {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    static boolean correctText = false;

    private static ArrayList<String> readingStrings = new ArrayList<>();


    private static char[] encryptAlphabet = new char[ALPHABET.length];

        static int key = 0;
        static String encryptFileName = "";
        static String bruteForceDecodedFileName = "";

/*
    static int key = 0; // !!! удалить при завершении разработки
    // !!! удалить при завершении разработки
    static String encryptFileName = "C:\\ELENA\\JR2\\Elena_Belyasnik_JavaRush_Project_1\\EncryptFile\\encrypt.txt";
    // !!! удалить при завершении разработки
    static String bruteForceDecodedFileName = "C:\\ELENA\\JR2\\Elena_Belyasnik_JavaRush_Project_1\\BrutForce\\BrutForceFile.txt";
*/

    //** Go through all the keys until we get the correct text
    public static void runAllKeys() {
        for (int k = 0; k < ALPHABET.length; k++) {
            //ArrayList<String> list = new ArrayList<>();
            key = k;
            initEncryptAlphabet();
            readAndDecode();
            if (checkText()) {
                writeIntoFile();
                break;
            }
        }
    }

    //** Fills in the alphabet for decoding
    public static void initEncryptAlphabet() {
        int length = encryptAlphabet.length;
        for (int i = 0; i < length; i++) {
            int delta = i + key;
            if (delta < length) {
                encryptAlphabet[i] = ALPHABET[delta];
            } else {
                encryptAlphabet[i] = ALPHABET[i + key - length];
            }
        }
    }

    //** Encrypt original file into ArrayList
    public static void readAndDecode() {
        try (BufferedReader reader = new BufferedReader(new FileReader(encryptFileName))) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase();
                // посимвольно расшифровать строку
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    for (int j = 0; j < encryptAlphabet.length; j++) {
                        if (chars[i] == encryptAlphabet[j]) {
                            chars[i] = ALPHABET[j];
                            break;
                        }
                    }
                }
                String s = String.valueOf(chars);
                readingStrings.add(s);
                System.out.println(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //** Checks text for correct words
    public static boolean checkText() {
        for (String s : readingStrings) {
            if (s.contains(" и ")) {
                correctText = true;
                return true;
            }
        }
        return false;
    }

    //** Write valid text to file
    public static void writeIntoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bruteForceDecodedFileName))) {
            for (String s : readingStrings) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
