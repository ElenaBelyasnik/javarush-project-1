import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

public class Dialogue {
    private static final int ENCRYPTING = 1;
    private static final int KEY_DECODING = 2;
    private static final int BRUTE_FORCE = 3;
    private static final int STAT_ANALIS = 4;
    private static final String EXIT = "5";


    public static void showMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int selectedMenuItem;
        String s = "";


        while (!EXIT.equals(s)) {
            outputMenuText();
            s = scanner.next();
            selectedMenuItem = inputInt(s);


            if (selectedMenuItem == ENCRYPTING) {
                encrypt();
            } else if (selectedMenuItem == KEY_DECODING) {
                keyDecode();
            } else if (selectedMenuItem == BRUTE_FORCE) {
                bruteForceDecode();
            } else if (selectedMenuItem == STAT_ANALIS) {
                statAnalisDecode();
            }

        }
        System.out.println("До свидания!");
    }


    //** Encrypting by key
    private static void encrypt() throws IOException {
        System.out.println("Шифрование");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите ключ шифрования:");
            int key = 0;
            try {
                key = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Ошибка при вводе числа");
                return;
            }


            // C:\TEMP\OrigFile\orig.txt
            System.out.println("Введите путь к файлу, который нужно зашифровать:");
            String fileName1 = reader.readLine();
            if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {
                return;
            }

            // C:\TEMP\EncryptFile\encrypt.txt
            System.out.println("Введите путь к файлу, в котором будет зашифрованный текст:");
            String fileName2 = reader.readLine();
            if (!checkPath(fileName2)) {
                return;
            }
            EncryptFile.start(key, fileName1, fileName2);
        }
    }

    //** Decoding by key
    private static void keyDecode() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Расшифровка текста с помощью ключа");
            System.out.println("Введите ключ для расшифровки текста:");
            int key = 0;
            try {
                key = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Ошибка при вводе числа");
                return;
            }
            // C:\TEMP\EncryptFile\encrypted.txt
            System.out.println("Введите путь к файлу, который нужно расшифровать:");
            String fileName1 = reader.readLine();
            if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {
                return;
            }

            // C:\TEMP\KeyDecoding\keyDecoded.txt
            System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
            String fileName2 = reader.readLine();
            if (!checkPath(fileName2)) {
                return;
            }
            KeyDecodingFile.startKeyDecoding(key, fileName1, fileName2);
        }
    }

    //** Decoding by "Brute Force"
    private static void bruteForceDecode() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Расшифровка текста с помощью brute force");
            // C:\TEMP\EncryptFile\encrypted.txt
            System.out.println("Введите путь к файлу, который нужно расшифровать:");
            String fileName1 = reader.readLine();
            if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {
                return;
            }

            // C:\TEMP\BrutForce\BrutForceFile.txt
            System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
            String fileName2 = reader.readLine();
            if (!checkPath(fileName2)) {
                return;
            }
            KeyDecodingFile.checkAllKeys(fileName1, fileName2);
        }
    }

    //** decryption using statistical text analysis
    private static void statAnalisDecode() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Расшифровка с помощью статистического анализа текста");

            // C:\TEMP\StatAnalisys\BigTextForStatAnalisis.txt
            System.out.println("Введите путь к файлу для сбора статистического анализа:");
            String fileName1 = reader.readLine();
            if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {
                return;
            }

            // C:\TEMP\EncryptFile\encrypted.txt
            System.out.println("Введите путь к файлу, который нужно расшифровать:");
            String fileName2 = reader.readLine();
            if (!checkPath(fileName2) | !checkFileExistense(Path.of(fileName2))) {
                return;
            }

            // C:\TEMP\StatAnalisys\DecodedFile.txt
            System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
            String fileName3 = reader.readLine();
            if (!checkPath(fileName3)) {
                return;
            }

            StatisticalAnalysis.start(fileName1, fileName2, fileName3);
        }
    }


    private static int inputInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.err.println("Неверный ввод целого числа");
            return 0;
        }
    }

    private static void outputMenuText() {
        System.out.println();
        System.out.println("1. Для шифрования текста введите 1");
        System.out.println("2. Для расшифровки текста с помощью ключа введите 2");
        System.out.println("3. Для расшифровки текста с помощью brute force введите 3");
        System.out.println("4. Для расшифровки с помощью статистического анализа текста введите 4");
        System.out.println("5. Для выхода из приложения введите 5");
    }

    private static boolean checkPath(String strPath) {
        HashSet<Path> paths = new HashSet<>(); // список системных папок
        paths.add(Path.of("\\Windows\\").toAbsolutePath());
        paths.add(Path.of("\\Program files\\").toAbsolutePath());
        paths.add(Path.of("\\Mycrosoft\\").toAbsolutePath());

        Path pathForCheck = Path.of(strPath);
        // проверка отсутствия в пути системных папок
        for (Path p : paths) {
            if (pathForCheck.startsWith(p)) {
                System.err.println("Путь не должен содержать системные папки");
                return false;
            }
        }
        return true;
    }

    // проверка наличия исходного файла для обработки
    private static boolean checkFileExistense(Path path) {
        // если указанного файла не существует, то - ошибка
        if (!Files.exists(path)) {
            System.err.println("Нет такого файла " + path);
            return false;
        }
        return true;
    }


}
