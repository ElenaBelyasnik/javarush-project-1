import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BrutForce {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    static boolean correctText = false;
    private static List<String> list = new ArrayList<String>();
    private static char[] encryptAlphabet = new char[ALPHABET.length];
    private static String encryptedFileName = "";
    private static String bruteForceDecodedFileName = "";

    public static void setEncryptedFileName(String encryptedFileName) {
        BrutForce.encryptedFileName = encryptedFileName;
    }

    public static void setBruteForceDecodedFileName(String bruteForceDecodedFileName) {
        BrutForce.bruteForceDecodedFileName = bruteForceDecodedFileName;
    }

    //** Go through all the keys until we get the correct text
    public static void checkAllKeys() throws IOException {
        int length = ALPHABET.length;
        for (int key = 0; key < length; key++) {

            initEncryptAlphabet(key);
            readIntoStringArray();

            if (checkText()) {
                writeIntoFile();
                System.out.println("Расшифровка успешно завершена!");
                break;
            } else if (key == length - 1) {
                System.out.println("Расшифровка не удалась...");
            }
        }
    }

    //** Fills in the alphabet for decoding
    private static void initEncryptAlphabet(int key) {
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
    public static void readIntoStringArray() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(encryptedFileName))) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase();
                list.add(decode(line));
            }
        }
    }

    //** decrypt a string
    private static String decode(String line) {
        char[] chars = line.toCharArray();
        for (int charsIndex = 0; charsIndex < chars.length; charsIndex++) {
            for (int alphPos = 0; alphPos < encryptAlphabet.length; alphPos++) {
                if (chars[charsIndex] == encryptAlphabet[alphPos]) {
                    chars[charsIndex] = ALPHABET[alphPos];
                    break;
                }
            }
        }
        return String.valueOf(chars);
    }


    //** Checks text for correct words
    public static boolean checkText() {
        for (String s : list) {
            if (s.contains(" и ")) {
                correctText = true;
                return true;
            }
        }
        return false;
    }

    //** Write valid text to file
    public static void writeIntoFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bruteForceDecodedFileName))) {
            for (String s : list) {
                writer.write(s);
                writer.newLine();
            }
        }
    }
}
