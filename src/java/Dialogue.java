import java.io.IOException;
import java.util.Scanner;

public class Dialogue {
    private static final int ENCRYPTING = 1;
    private static final int KEY_DECODING = 2;
    private static final int BRUTE_FORCE = 3;
    private static final int STAT_ANALIS = 4;
    private static final String EXIT = "5";


    public static void showMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int commandKey = 0;
        String s = "";


        while (!EXIT.equals(s)) {
            System.out.println();
            System.out.println("1. Для шифрования текста введите 1");
            System.out.println("2. Для расшифровки текста с помощью ключа введите 2");
            System.out.println("3. Для расшифровки текста с помощью brute force введите 3");
            System.out.println("4. Для расшифровки с помощью статистического анализа текста введите 4");
            System.out.println("5. Для выхода из приложения введите 5");

            s = scanner.next();

            commandKey = inputInt(s);

            switch (commandKey) {
                case ENCRYPTING:
                    encrypt();
                    break;
                case KEY_DECODING:
                    keyDecode();
                    break;
                case BRUTE_FORCE:
                    bruteForceDecode();
                    break;
                case STAT_ANALIS:
                    statAnalisDecode();
            }
        }
        System.out.println("До свидания!");
    }

    //** menuItem1: Input key, origFileName, encriptFileName
    public static void encrypt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Шифрование");
        System.out.println("Введите ключ шифрования:");
        int key = inputInt(scanner.next());
        EncryptFile.setKey(key);
        // C:\TEMP\OrigFile\orig.txt
        System.out.println("Введите путь к файлу, который нужно зашифровать:");
        EncryptFile.setOrigFileName(scanner.nextLine());
        // C:\TEMP\EncryptFile\encrypt.txt
        System.out.println("Введите путь к файлу, в котором будет зашифрованный текст:");
        EncryptFile.setEncryptFileName(scanner.nextLine());
        EncryptFile.initEncryptAlphabet();
        EncryptFile.encrypt();
    }

    //** menuItem2: Input key, origFileName, encriptFileName
    public static void keyDecode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Расшифровка текста с помощью ключа");
        System.out.println("Введите ключ для расшифровки текста:");
        int key = inputInt(scanner.next());
        KeyDecodingFile.setKey(key);
        // C:\TEMP\EncryptFile\encrypt.txt
        System.out.println("Введите путь к файлу, который нужно расшифровать:");
        KeyDecodingFile.setEncryptFileName(scanner.nextLine());
        // C:\TEMP\KeyDecoding\keyDecodedFile.txt
        System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
        KeyDecodingFile.setKeyDecodedFileName(scanner.nextLine());
        KeyDecodingFile.initParam();
        KeyDecodingFile.decode();
    }

    //** menuItem3: Input key, origFileName, encriptFileName
    public static void bruteForceDecode() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Расшифровка текста с помощью brute force");
        // C:\TEMP\EncryptFile\encrypt.txt
        System.out.println("Введите путь к файлу, который нужно расшифровать:");
        BrutForce.setEncryptedFileName(scanner.nextLine());
        // C:\TEMP\BrutForce\BrutForceFile.txt
        System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
        BrutForce.setBruteForceDecodedFileName(scanner.nextLine());
        BrutForce.checkAllKeys();
    }

    private static void statAnalisDecode() {
        System.out.println("Расшифровки с помощью статистического анализа текста");
    }

    public static int inputInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.err.println("Неверный ввод целого числа");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
}
