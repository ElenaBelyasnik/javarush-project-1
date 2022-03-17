import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Dialogue {
    public static void menu() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int x = 0;
        String s = "";

        while (!"5".equals(s)) {
            System.out.println("1. Для шифрования текста введите 1");
            System.out.println("2. Для расшифровки текста с помощью ключа введите 2");
            System.out.println("3. Для расшифровки текста с помощью brute force введите 3");
            System.out.println("4. Для расшифровки с помощью статистического анализа текста введите 4");
            System.out.println("5. Для выхода из приложения введите 5");
            s = scan.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }

            switch (x) {
                case 1:
                    System.out.println("Шифрование:");
                    inputParam1(); // !!! раскомментировать при окончании разработки
                    EncryptFile.initParam();
                    EncryptFile.encrypt();
                    break;
                case 2:
                    System.out.println("Расшифровка текста с помощью ключа:");
                    inputParam2(); // !!! раскомментировать при окончании разработки
                    KeyDecodingFile.initParam();
                    KeyDecodingFile.decode();
                    break;
                case 3:
                    System.out.println("Расшифровка текста с помощью brute force:");
                    inputParam3(); // !!! раскомментировать при окончании разработки
                    BrutForce.runAllKeys();
                    break;
                case 4:
                    System.out.println("Расшифровки с помощью статистического анализа текста");
            }
        }
        System.out.println("До свидания!");
    }

    //** menuItem1: Input key, origFileName, encriptFileName
    public static void inputParam1() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Введите ключ шифрования:");
            EncryptFile.key = Integer.parseInt(reader.readLine());
            // C:\OrigFile\orig.txt
            System.out.println("Введите путь к файлу, который нужно зашифровать:");
            EncryptFile.origFileName = reader.readLine();
            // C:\EncryptFile\encrypt.txt
            System.out.println("Введите путь к файлу, в котором будет зашифрованный текст:");
            EncryptFile.encrFileName = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //** menuItem2: Input key, origFileName, encriptFileName
    public static void inputParam2() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите ключ для расшифровки текста:");
            KeyDecodingFile.key = Integer.parseInt(reader.readLine());

            // C:\EncryptFile\encrypt.txt
            System.out.println("Введите путь к файлу, который нужно расшифровать:");
            KeyDecodingFile.encryptFileName = reader.readLine();
            // C:\KeyDecoding\keyDecodedFile.txt
            System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
            KeyDecodingFile.keyDecodedFileName = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //** menuItem3: Input key, origFileName, encriptFileName
    public static void inputParam3() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // C:\EncryptFile\encrypt.txt
            System.out.println("Введите путь к файлу, который нужно расшифровать:");
            BrutForce.encryptFileName = reader.readLine();
            // C:\BrutForce\BrutForceFile.txt
            System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
            BrutForce.bruteForceDecodedFileName = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
