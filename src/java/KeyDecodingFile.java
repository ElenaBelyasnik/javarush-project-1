import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KeyDecodingFile {

    private static char[] encryptAlphabet = new char[Main.ALPHABET.length];
    private static int key = 0;
    private static String encryptFileName = null;
    private static String keyDecodedFileName = null;
    private static boolean correctText = false;
    private static List<String> list = new ArrayList<>();

    public static List<String> getList() {
        return list;
    }

    public static boolean isCorrectText() {
        return correctText;
    }

    public static void setEncryptAlphabet() {
        int length = encryptAlphabet.length;
        for (int i = 0; i < length; i++) {
            int delta = i + key;
            if (delta < length) {
                encryptAlphabet[i] = Main.ALPHABET[delta];
            } else {
                encryptAlphabet[i] = Main.ALPHABET[i + key - length];
            }
        }
    }

    public static void startKeyDecoding(int k, String encryptedFileName, String decodedFileName) throws IOException {
        start(k, encryptedFileName, decodedFileName);
        resume();
    }

    public static void start(int k, String encryptedFileName, String decodedFileName) throws IOException {
        int length = encryptAlphabet.length;
        key = k;
        encryptFileName = encryptedFileName;
        keyDecodedFileName = decodedFileName;
        setEncryptAlphabet();
        readIntoStringArray(list, encryptedFileName);
        decodeList(list);
        checkText(list);
    }

    // проверка текста, если успешно, то пишем в файл
    public static void resume() throws IOException {

        if (correctText) {
            writeIntoFile(list, keyDecodedFileName);
            System.out.println("Расшифровка успешно завершена!");
        } else {
            System.out.println("Расшифровка не удалась...");
        }
    }

    //** BrutForce: Go through all the keys until we get the correct text
    public static void checkAllKeys(String encryptedFileName, String bruteForceDecodedFileName) throws IOException {
        int length = Main.ALPHABET.length;
        for (int key = 0; key < length; key++) {
            start(key, encryptedFileName, bruteForceDecodedFileName);
            if (isCorrectText()) {
                writeIntoFile(getList(), bruteForceDecodedFileName);
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
                encryptAlphabet[i] = Main.ALPHABET[delta];
            } else {
                encryptAlphabet[i] = Main.ALPHABET[i + key - length];
            }
        }
    }

    //** Encrypt original file into ArrayList
    public static List<String> readIntoStringArray(List<String> list, String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase();
                list.add(decode(line));
            }
        }
        return list;
    }

    public static void decodeList(List<String> list) {
        for (int lineNum = 0; lineNum < list.size(); lineNum++) {
            decode(list.get(lineNum));
        }
    }

    //** decrypt a string
    public static String decode(String line) {
        char[] chars = line.toCharArray();
        for (int charsIndex = 0; charsIndex < chars.length; charsIndex++) {
            for (int alphPos = 0; alphPos < encryptAlphabet.length; alphPos++) {
                if (chars[charsIndex] == encryptAlphabet[alphPos]) {
                    chars[charsIndex] = Main.ALPHABET[alphPos];
                    break;
                }
            }
        }
        return String.valueOf(chars);
    }

    //** Checks text for correct words
    public static boolean checkText(List<String> list) {
        for (String s : list) {
            if (s.contains(" и ")) {
                correctText = true;
                return true;
            }
        }
        return false;
    }


    //** Write valid text to file
    public static void writeIntoFile(List<String> list, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            for (String s : list) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

}
