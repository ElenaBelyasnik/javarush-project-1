import java.io.*;

public class EncryptFile {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static char[] encrAlphabet = new char[ALPHABET.length];
    static int key = 0;
    static String origFileName = "";
    static String encrFileName = "";

/*
    static int key = 3; // !!! удалить при завершении разработки
    // !!! удалить при завершении разработки
    static String origFileName = "C:\\ELENA\\JR2\\Elena_Belyasnik_JavaRush_Project_1\\OrigFile\\orig.txt";
    // !!! удалить при завершении разработки
    static String encrFileName = "C:\\ELENA\\JR2\\Elena_Belyasnik_JavaRush_Project_1\\EncryptFile\\encrypt.txt";
*/

    //** Init start params: origFile, encriptFile...
    public static void initParam() {
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
             BufferedWriter writer = new BufferedWriter(new FileWriter(encrFileName))
        ) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase();
                // посимвольно шифруем строку
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    for (int j = 0; j < ALPHABET.length; j++) {
                        if (chars[i] == ALPHABET[j]) {
                            chars[i] = encrAlphabet[j];
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
