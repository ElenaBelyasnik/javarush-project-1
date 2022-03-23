import java.io.*;

public class EncryptFile {
    private static char[] encrAlphabet = new char[Main.ALPHABET.length];
    private static int key = 0;
    private static String origFileName = "";
    private static String encryptedFileName = "";

    private static void setEncrAlphabet(int key) {
        if (key > Main.ALPHABET.length) key = Main.ALPHABET.length;
        int length = encrAlphabet.length;
        for (int i = 0; i < length; i++) {
            int delta = i + key;
            if (delta < length) {
                encrAlphabet[i] = Main.ALPHABET[delta];
            } else {
                encrAlphabet[i] = Main.ALPHABET[i + key - length];
            }
        }
    }

    public static void start(int k, String origFile, String encryptFile) {
        key = k;
        origFileName = origFile;
        encryptedFileName = encryptFile;

        setEncrAlphabet(key);
        encrypt();
    }

    //** Encrypt original file
    private static void encrypt() {
        try (BufferedReader reader = new BufferedReader(new FileReader(origFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(encryptedFileName))
        ) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase().replace('ё', 'е');
                // посимвольно шифруем строку
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int pos = -1; // position in orig alphabet
                    for (int posInAlphabet = 0; posInAlphabet < Main.ALPHABET.length; posInAlphabet++) {
                        if (chars[i] == Main.ALPHABET[posInAlphabet]) {
                            pos = posInAlphabet;
                            chars[i] = encrAlphabet[posInAlphabet];
                            break;
                        }
                    }
                    if (pos == -1) {
                        chars[i] = '#';
                    }
                }
                // внести зашифрованную строку в файл для зашифрованного текста
                writer.write(chars, 0, chars.length);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
