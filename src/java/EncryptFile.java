import java.io.*;

public class EncryptFile {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static char[] encrAlphabet = new char[ALPHABET.length];
    static int key = 0;
    static String origFileName = "";
    static String encryptFileName = "";

    public static void setKey(int key) {
        EncryptFile.key = key;
    }

    public static void setOrigFileName(String origFileName) {
        EncryptFile.origFileName = origFileName;
    }

    public static void setEncryptFileName(String encryptFileName) {
        EncryptFile.encryptFileName = encryptFileName;
    }

    //** Init start params: origFile, encriptFile...
    public static void initEncryptAlphabet() {
        if (key > ALPHABET.length) key = ALPHABET.length;
        int length = encrAlphabet.length;
        for (int i = 0; i < length; i++) {
            int delta = i + key;
            if (delta < length) {
                encrAlphabet[i] = ALPHABET[delta];
            } else {
                encrAlphabet[i] = ALPHABET[i + key - length];
            }
        }
    }

    //** Encrypt original file
    public static void encrypt() {
        try (BufferedReader reader = new BufferedReader(new FileReader(origFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(encryptFileName))
        ) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase().replace('ё', 'е');
                // посимвольно шифруем строку
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int pos = -1; // position in orig alphabet
                    for (int posInAlphabet = 0; posInAlphabet < ALPHABET.length; posInAlphabet++) {
                        if (chars[i] == ALPHABET[posInAlphabet]) {
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
