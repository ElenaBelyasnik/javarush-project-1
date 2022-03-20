import java.io.IOException;
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
        int selectedMenuItem = 0;
        String s = "";


        while (!EXIT.equals(s)) {
            inputMenuText();
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
    public static void encrypt() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Шифрование");
        System.out.println("Введите ключ шифрования:");
        int key = inputInt(scanner.nextLine());
        if (key == 0) return;
        EncryptFile.setKey(key);

        // C:\TEMP\OrigFile\orig.txt
        System.out.println("Введите путь к файлу, который нужно зашифровать:");
        String fileName1 = scanner.nextLine();
        if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {return;}
        EncryptFile.setOrigFileName(fileName1);

        // C:\TEMP\EncryptFile\encrypt.txt
        System.out.println("Введите путь к файлу, в котором будет зашифрованный текст:");
        String fileName2 = scanner.nextLine();
        if (!checkPath(fileName2)) {return;}
        EncryptFile.setEncryptFileName(fileName2);
        EncryptFile.initEncryptAlphabet();
        EncryptFile.encrypt();
    }

    //** Decoding by key
    public static void keyDecode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Расшифровка текста с помощью ключа");
        System.out.println("Введите ключ для расшифровки текста:");
        int key = inputInt(scanner.nextLine());
        if (key == 0) return;
        KeyDecodingFile.setKey(key);

        // C:\TEMP\EncryptFile\encrypt.txt
        System.out.println("Введите путь к файлу, который нужно расшифровать:");
        String fileName1 = scanner.nextLine();
        if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {return;}
        KeyDecodingFile.setEncryptFileName(fileName1);

        // C:\TEMP\KeyDecoding\keyDecodedFile.txt
        System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
        String fileName2 = scanner.nextLine();
        if (!checkPath(fileName2)) {return;}
        KeyDecodingFile.setKeyDecodedFileName(fileName2);

        KeyDecodingFile.initParam();
        KeyDecodingFile.decode();
    }

    //** Decoding by "Brute Force"
    public static void bruteForceDecode() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Расшифровка текста с помощью brute force");
        // C:\TEMP\EncryptFile\encrypt.txt
        System.out.println("Введите путь к файлу, который нужно расшифровать:");
        String fileName1 = scanner.nextLine();
        if (!checkPath(fileName1) | !checkFileExistense(Path.of(fileName1))) {return;}
        BrutForce.setEncryptedFileName(scanner.nextLine());

        // C:\TEMP\BrutForce\BrutForceFile.txt
        System.out.println("Введите путь к файлу, в котором будет расшифрованный текст:");
        String fileName2 = scanner.nextLine();
        if (!checkPath(fileName2)) {return;}
        BrutForce.setBruteForceDecodedFileName(fileName2);
        BrutForce.checkAllKeys();
    }

    //** decryption using statistical text analysis
    private static void statAnalisDecode() {
        System.out.println("Расшифровки с помощью статистического анализа текста");
    }


    public static int inputInt(String s) throws NumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.err.println("Неверный ввод целого числа");
            return 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    private static void inputMenuText() {
        System.out.println();
        System.out.println("1. Для шифрования текста введите 1");
        System.out.println("2. Для расшифровки текста с помощью ключа введите 2");
        System.out.println("3. Для расшифровки текста с помощью brute force введите 3");
        System.out.println("4. Для расшифровки с помощью статистического анализа текста введите 4");
        System.out.println("5. Для выхода из приложения введите 5");
    }

    public static boolean checkPath(String strPath){
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
        // если не указано имя файла
        if (!Files.isRegularFile(pathForCheck)) {
            System.err.println("Путь должен содержать имя файла");
            return false;
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
