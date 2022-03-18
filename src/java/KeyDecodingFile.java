import java.io.*;

public class KeyDecodingFile {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static char[] encryptAlphabet = new char[ALPHABET.length];

    static int key = 0;
    static String encryptFileName = "";
    static String keyDecodedFileName = "";

    public static void setKey(int key) {
        KeyDecodingFile.key = key;
    }

    public static void setEncryptFileName(String encryptFileName) {
        KeyDecodingFile.encryptFileName = encryptFileName;
    }

    public static void setKeyDecodedFileName(String keyDecodedFileName) {
        KeyDecodingFile.keyDecodedFileName = keyDecodedFileName;
    }

    //** Init start params: origFile, encriptFile...
    public static void initParam() {
        if (key > ALPHABET.length) key = ALPHABET.length;
        int length = encryptAlphabet.length;
        for (int i = 0; i < length; i++) {
            int delta = i + key;
            if (delta < length) {
                encryptAlphabet[i] = ALPHABET[delta];
            } else {
                encryptAlphabet[i] = ALPHABET[i + key - length];
            }
        }
        //System.out.println(Arrays.toString(ALPHABET)); // !!! убрать  в конце разработки
        //System.out.println(Arrays.toString(encrAlphabet)); // !!! убрать в конце разработки
    }

    //** Encrypt original file
    public static void decode() {
        try (BufferedReader reader = new BufferedReader(new FileReader(encryptFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(keyDecodedFileName))
        ) {
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
                // внести зашифрованную строку в файл для зашифрованного текста
                    writer.write(chars, 0, chars.length);
                    writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
