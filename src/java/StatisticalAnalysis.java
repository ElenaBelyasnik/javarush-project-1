import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StatisticalAnalysis {

    private static String statAnalisisFileName = ""; // путь к файлу, с большим текстом для сбора статистики
    private static String encryptedFileName = ""; // путь к файлу с зашифрованным текстом
    private static String decodedFileName = ""; // путь к файлу с результатом расшифровки

    private static HashMap<Character, Integer> bigFileStatistic = new HashMap<>();
    private static ArrayList<Character> bigCharStat = new ArrayList<>();
    private static ArrayList<Integer> bigCountStat = new ArrayList<>();

    private static HashMap<Character, Integer> encryptedFileStatistic = new HashMap<>();
    private static ArrayList<Character> encryptedCharStat = new ArrayList<>();
    private static ArrayList<Integer> encryptedCountStat = new ArrayList<>();

    public static void start(String bigTextFileName, String encryptFileName, String decodeFileName) throws IOException {
         statAnalisisFileName = bigTextFileName;
         encryptedFileName = encryptFileName;
         decodedFileName = decodeFileName;

        List<String> bigTextStrList = new ArrayList<>();
        List<String> encrypedStrList = new ArrayList<>();
        // прочитать файл для сбора статистики в массив строк
        bigTextStrList = readIntoStringArray(bigTextStrList, statAnalisisFileName);
        // собрать статистику по большому файлу для сбора статистики
        getStatistic(bigTextStrList, bigFileStatistic);
        sortMap(bigFileStatistic, bigCharStat, bigCountStat);

        // прочитать зашифрованный файл в массив строк
        encrypedStrList = readIntoStringArray(encrypedStrList, encryptedFileName);
        // собрать статистику по зашифрованному файлу
        getStatistic(encrypedStrList, encryptedFileStatistic);
        sortMap(encryptedFileStatistic, encryptedCharStat, encryptedCountStat);

        // пробуем расшифровать...
        tryToDecript();
        // если удалось зашифровать, то пишем в файл, если нет - выдаём сообщение о фиаско
        KeyDecodingFile.resume();

    }

    //** Collect file into ArrayList
    private static List<String> readIntoStringArray(List<String> list, String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String line = reader.readLine().toLowerCase();
                list.add(line);
            }
        }
        return list;
    }

    //** собрать статистику по файлу
    private static void getStatistic(List<String> list, HashMap<Character, Integer> map) {
        // проходим по каждой строке текста и собираем статистику по символам строки
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            char[] chars = list.get(i).toCharArray();
            for (int charsIndex = 0; charsIndex < chars.length; charsIndex++) {
                // здесь надо каждый символ добавить в map со счётчиком частоты употребления
                Character key = chars[charsIndex];
                if (map.containsKey(key)) {
                    // если он там есть, то увеличить счётчик на 1
                    Integer value = map.get(key) + 1;
                    map.put(chars[charsIndex], value);
                                    }
                else {
                    // если такого символа нет в статистике, то добавляем его как ключ со значением 1
                    map.put(chars[charsIndex], 1);
                }
            }
        }
    }

    // в результате сортировки HashMap по значению получим:
    // отсортированный по убыванию ArrayList со значениями частоты повторяемости
    // и соответствующий ArrayList c символами
    private static void sortMap (HashMap<Character, Integer> map, ArrayList<Character> characters, ArrayList <Integer> counts) {
        for (HashMap.Entry<Character, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            boolean isAdded = false;
            for (int i = 0; i < counts.size(); i++) {
                if (value > counts.get(i)) {
                    counts.add(i, value);
                    characters.add(i, e.getKey());
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                counts.add(value);
                characters.add(e.getKey());
            }
        }
    }

    // пытаемся расшифровать
    private static void tryToDecript () throws IOException {
        // во внешнем цикле идём по символам статистики большого текста
        // отсортированным по убыванию частоты повторяемости
        int alphabetLength = Main.ALPHABET.length;
        for (int pos = 0; pos < bigCharStat.size(); pos++) {
            Character chr = bigCharStat.get(pos);
            // найдём позицию символа из статистики в алфавите
            int posBigTextChrInAlph = findPosInAlphavit(chr);
            if (posBigTextChrInAlph == -1) {
                continue;
            }
            // во внутреннем цикле идём по символам статистики зашифрованного текста,
            // отсортированным по убыванию частоты повторяемости
        for (int posEncr = 0; posEncr < encryptedCharStat.size(); posEncr++) {

            Character charFromEncrText = encryptedCharStat.get(posEncr);

            int posEncryptChInAlph = findPosInAlphavit(charFromEncrText);
            if (posBigTextChrInAlph == -1) {
                continue;
            }

            // вычислить ключ
                int key = posEncryptChInAlph + alphabetLength - posBigTextChrInAlph;

                // пробуем расшифровать по ключу
                KeyDecodingFile.start(key, encryptedFileName, decodedFileName);
                if (KeyDecodingFile.isCorrectText()) {
                    break;
                }
            }
            if (KeyDecodingFile.isCorrectText()) {
                break;
            }
        }
    }

    // наийти  позицию символа а алфавите
    private static int findPosInAlphavit(Character chr) {
        int code = (int) chr;
        for (int pos = 0; pos < Main.ALPHABET.length; pos++) {
            int code1 = (int)Main.ALPHABET[pos];
            if (code == code1) {
                return pos;
            }
        }
        return -1;
    }

}
