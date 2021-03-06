# javarush-project-1
Some description for JR first project.

###Немного теории

https://javarush-team.notion.site/javarush-team/Road-map-f0135a266781448081f797862abf00f4

# Road map ****криптоанализатор****

### **Общее описание задания**

Задача: написать программу, которая работает с шифром Цезаря.

Это один из самых простых и известных методов шифрования. Назвали его, само собой, в честь императора Гая Юлия Цезаря, который применял его для секретной переписки с генералами.

Шифр Цезаря — это шифр подстановки: в нем каждый символ в открытом тексте заменяется на символ, который находится на некотором постоянном числе позиций левее или правее него в алфавите.

Допустим, мы устанавливаем сдвиг на 3 (ключ = 3). В таком случае А заменится на Г, Б станет Д, и так далее.

Давай для наглядности рассмотрим игрушку для шифрования кодом Цезаря:

**Она состоит из двух кругов которые вращаются относительно друг друга; По периметру каждого из кругов написаны буквы в порядке алфавита. При смещении все буквы внутреннего круга смещаются на одинаковое расстояние относительно букв внешнего круга.**

**Для шифрования мы буквы из внешнего круга заменяем на стоящие рядом с ними буквы из нижнего круга. Например, если круги смещены как на картинке, А заменяем на С, а R - на T. А чтобы расшифровать все делаем наоборот: буквы из внутреннего круга заменяем на буквы из внешнего; то есть, С обратно на А, и Т обратно на R.**

**Допустим, песик Бобик решил написать девочке Алисе секретное сообщение. У каждого из них есть такая игрушка. Чтобы успешно зашифровать и расшифровать сообщение, они должны договориться на сколько позиций надо сместить круги своих игрушек относительно друг-друга.  На картинке игрушки видно что А стала С, а В стала D, значит внутренний круг смещен на 2 позиции. Заметьте, что Y стала А (и не улетела в космос); Когда они договорились сместить круги на 2 позиции и Бобик передал Алисе надпись NGVU RNCA NGIQ, Алиса сразу поняла и пошла доставать коробку в Lego.**

**Так как в английском алфавите только 26 букв, можно сместить круги на 1,2,...,25 позиций. Если мы сместим на 26 (или 0) позиций то буквы на окружностях совпадут.**

Это минимум теоретических данных, которые понадобятся тебе для выполнения итогового проекта. Переходим к описанию задания!

### **Криптоанализ - взлом шифра**

**Криптография - алгоритмы шифрования данных (и не только)**

Алфавит - полный набор символов, которые могут попасться в тексте, поддающемся шифровке. В шифре Цезаря важен порядок этих символов. Он может и не быть классическим порядком букв в алфавите, но его должны знать и Бобик (тот кто шифрует) и Алиса (тот кто расшифрует);

Криптоанализ статистических данных основан на том, что в каждом языке есть своя статистика использования символов. Например, в этом тексте (пока я его пишу) уже есть 14 слов “и” и 24 слова “в”. Если сохранять пробелы, то легко догадаться, что наиболее частые слова из одной буквы это

скорее всего В или К или И. И если использовали шифр цезаря с классическим порядком букв в алфавите, расшифровать этот текст будет элементарно (попробуем сдвиг для В - нечитабельно -тогда попробуем сдвиг для К, и так далее).

Хорошо, можно съедать пробелы, чтобы спрятать слова из 1 буквы. Но тогда можно легко определить гласные (они встречаются намного чаще согласных), или часто встречающиеся слова вроде ИЛИ. Даже простой перебор не составит трудностей тк всего существует очень маленькое число вариантов для подбора (для английского алфавита 25). Поэтому Бобику и Алисе следует попробовать более сложные метод шифрования.

Отходить от буквального задания - можно. Главное - суть.

**Результат: программа работает в нескольких режимах**

**Режимы:**

1. **шифровка текста**
2. **Расшифровка текста с помощью ключа**
3. **Расшифровка текста с помощью brute force (перебор всех вариантов)**
4. **(дополнительно) расшифровка с помощью статистического анализа текста**

**Программа должна открывать указанный пользователем файл с текстом и проделывать с ним одно из указанных выше действий. После этого создавать новый файл с результатом.**

**В программе должен быть пользовательский интерфейс.**

**Это может быть меню в командной строке или графический интерфейс, если у вас есть время заморочиться, например, с JavaFX или Swing. Если нет желания делать графический интерфейс, можно просто организовать вывод меню в командную строку. Или же посмотрите на Picocli ([https://picocli.info/quick-guide.html](https://picocli.info/quick-guide.html) ).**

**Программа должна выполнять следующие функции:**

- **Шифровка текста из заданного файла. На вход она получает адрес файла с оригинальным текстом, адрес файла в который нужно записать зашифрованный текст, и сдвиг по алфавиту (это является ключом шифра Цезаря). Не забудьте проделать проверку того что а) файл оригинала по заданному адресу существует, и б) ключ от 0 и до (размер алфавита - 1) (или можете взять остаток от деления на размер алфавита).**
- **Расшифровка при известном ключе. На входе - адрес зашифрованного файла и адрес куда писать расшифрованный файл, а также сдвиг по алфавиту который использовался при шифровании (ключ).**
- **Расшифровка методом brute force (перебором всех возможных сдвигов); На входе - адрес зашифрованного файла, (опционально) адрес файла с текстом который является примером текста что был зашифрован (например другой труд того же автора) и адрес файла который должен содержать расшифрованный текст.**
- **Расшифровка методом статистического анализа; На входе тоже самое что и для расшифровке перебором.**

**Не забудьте проделать валидацию входных данных.**

Исходный текст для шифрования должен быть в файле. Желательно в формате txt. программа должна уметь работать с большими текстами на сотни страниц. Этот файл программа должна уметь зашифровать и записать зашифрованный текст в другой файл.

**Как должна работать программа после запуска:**

**Перед пользователем появляется меню с режимами. Пользователь выбирает режим (“шифровка текста”, “расшифровка с помощью ключа”, расшифровка с помощью brute force и файл с которым будут проводиться манипуляции. Также он должен иметь возможность задавать ключ в случае если он выбрал режим шифрования текста.**

**Подзадачи**

1. Создай алфавит, в котором существует задача. По условию это русский алфавит и пунктуация(**. , ” ’ : - ! ? ПРОБЕЛ)**. Не забываем про пробел!

Как это можно сделать? Например, можно загнать алфавит в String, несколько строк или массив String

Можно загнать алфавит во множество (Set), в массив или List. А ещё можно воспользоваться таблицей ASCII.

Помни, что алфавит не меняется, поэтому такую переменную можно сделать константой (public static final). Имя таких переменных принято писать заглавными буквами.

a еще лучше просто массив (так как алфавит не меняется, нет никакого смысла засовывать его в список), массивы занимают меньше памяти, и поэтому, по возможности, надо использовать именно массивы, особенно если речь идет о примитивных типах (в списках примитивные типа оборачиваются в объекты и занимают значительно больше памяти).

### Шифровка

Для нее нужно знать сдвиг (ключ) и алфавит.

Для каждого символа оригинального текста нужно -

- проверить что он есть в вашем алфавите. Если его нет, пропускаем этот символ.
- найти его позицию в алфавите. Подумайте, какую структуру данных нужно использовать чтобы ускорить этот процесс (раз в 15), ведь необязательно же сканировать всю библиотеку в поисках книги на букву Ы (Ладно, П).
- найти символ на позиции смещенной на заданный сдвиг. И помним что в примере с игрушкой Y стала А (и не улетела в космос). Как это гарантировать? (можно сделать (позиция буквы + сдвиг) %( размер алфавита). Процент - оператор получения остатка от деления).
- заменить оригинальный символ на зашифрованный

Сохранить результат в файл (чтобы избежать плохого пользователя который попробует зафигачить тебе .bash_profile или hosts валидируй имя файла вывода!)

### Расшифровка

Для нее нужно знать сдвиг (ключ) и алфавит.

Для каждого символа зашифрованного текста нужно -

- проверить что он есть в вашем алфавите. Если нет - вас ломают хакеры. Паникуйте (ну или верните ошибку).
- найти его позицию в алфавите.
- найти символ на позиции смещенной на заданный сдвиг (но только помните - вы не пытаетесь еще раз зашифровать шифр - поэтому сдвигаем в другую сторону).
- заменить зашифрованный символ на расшифрованный

Вы будете этот код использовать в следующий под-задачах, поэтому выводить результат в поток.

Сохранить результат в файл.

### Взлом (Brute Force)

Вы можете использовать код который написали для расшифровки при известном ключе, подставляя все возможные значения ключа.

Но как понять получилось ли расшифровать? Используйте пример текста (репрезентативный текст автора или в том же стиле); можете составить словарь слов и составить метрику основанную на том сколько слов совпало и какой они длины; или иную метрику которая изучает длину слов и предложений, или посмотреть какие буквы чаще всего предшествуют каким буквам или словарь наиболее частых начал слова (3 буквы), можно вообще не использовать никаких репрезентативных файлов и проверить правильность пунктуации и пробелов; Вариант с наилучшими результатами сохраните в файл вывода.

### Взлом (Статистический анализ)

Используйте пример текста (репрезентативный текст автора или в том же стиле) и составьте статистику букв (например, как часто встречается на каждые 1000 символов); (Кстати. Легко взломать шифр и без такого файла и анализа - попробуйте угадать пробел - это наверняка наиболее часто встречающийся символ в обычном тексте.)

Далее составьте такую же статистику для зашифрованного текста. Учтите, просто считать символы не достаточно так как тексты могут быть разной длины!

Далее посчитайте отклонение для каждого возможного сдвига зашифрованной статистики относительно репрезентативной (можно для этого использовать сумму квадратов отклонения или дот-произведение векторов). Найдите сдвиг дающий минимальное отклонение и расшифруйте используя этот сдвиг.

# Какую задачу решает проект?

Проект работает в нескольких режимах.


**Режимы:**

1. Шифровка текста;
2. Расшифровка текста с помощью ключа;
3. Расшифровка текста с помощью brute force (перебор всех вариантов);
4. (Дополнительно) расшифровка с помощью статистического анализа текста.

Программа открывает указанный пользователем файл с текстом и проделывать с ним одно из указанных выше действий. После этого создавать новый файл с результатом.


# На какие классы обратить внимание

Проект состоит из следующих классов: 

1. **Main** - класс, в котором содержится запускаемый модуль public *static void main(String[] args)*. 
2. **EncryptFile** - класс, методы которого запускаются при выборе пользователем пункта меню 1 (Шифровка текста).
3. **KeyDecodingFile** - класс, методы которого запускаются при выборе пользователем пункта меню 2 (Расшифровка текста с помощью ключа) 	и  (Расшифровка текста с помощью brute force (перебор всех вариантов))
4. **StatisticalAnalysis** - класс, методы которого запускаются при выборе пользователем пункта меню 4 (Расшифровка с помощью
  	статистического анализа текста)

# Как собрать и запустить проект

## Запуск программы

Для запуска программы, нужно в коммандном окне ввести комманду c указанием полного пути к jar-файлу проекта: 

    **java -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -jar nameOfJar.jar** 

### Например, с указанием доп. параметров кодировки UTF-8: 

    *java -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -jar C:\ELENA\javarush-project-1\out\artifacts\javarush_project_1_jar\javarush-project-1.jar*
	
*Примечание:* для удобства запуска, рекомендуется внести команду запуска приложения в ****.bat***-файл.

# Примеры использования и результаты выполнения программы

Результаты выполнения 

При запуске на экране отобразится меню:

1. Для шифрования текста введите 1
2. Для расшифровки текста с помощью ключа введите 2
3. Для расшифровки текста с помощью brute force введите 3
4. Для расшифровки с помощью статистического анализа текста введите 4
5. Для выхода из приложения введите 5

Рассмотрим подробно все пункты.

## 1. Шифровка текста 

Для шифрования текста необходимо ввести 1.
Далее последовательно ввести ключ шифрования и пути к файлу, который будет шифроваться и файлу, в котором будет результат шифровки. 

### Пример диалога, в котором жирным шрифтом выделены ответы пользователя: 

1. Для шифрования текста введите 1
2. Для расшифровки текста с помощью ключа введите 2
3. Для расшифровки текста с помощью brute force введите 3
4. Для расшифровки с помощью статистического анализа текста введите 4
5. Для выхода из приложения введите 5

**1**

Шифрование
Введите ключ шифрования:

**3**

Введите путь к файлу, который нужно зашифровать:

**C:\TEMP\OrigFile\orig.txt**

Введите путь к файлу, в котором будет зашифрованный текст:

**C:\TEMP\EncryptFile\encrypt.txt**




## 2. Расшифровка текста с помощью ключа

Для расшифровки текста с помощью ключа необходимо ввести 2.
Далее последовательно ввести ключ шифрования и пути к зашифрованному файлу и файлу, в котором будет результат расшифровки. 

### Пример диалога, в котором жирным шрифтом выделены ответы пользователя: 

1. Для шифрования текста введите 1
2. Для расшифровки текста с помощью ключа введите 2
3. Для расшифровки текста с помощью brute force введите 3
4. Для расшифровки с помощью статистического анализа текста введите 4
5. Для выхода из приложения введите 5

**2**

Расшифровка текста с помощью ключа

Введите ключ для расшифровки текста:

**3**

Введите путь к файлу, который нужно расшифровать:

**C:\TEMP\EncryptFile\encrypt.txt**

Введите путь к файлу, в котором будет расшифрованный текст:

**C:\TEMP\KeyDecoding\keyDecodedFile.txt**

Файл расшифрован



## 3. Расшифровка текста с помощью brute force (перебор всех вариантов)

Для расшифровки текста с помощью  методом brute force (перебор всех вариантов) необходимо ввести 3.
Далее последовательно ввести ключ шифрования и пути к зашифрованному файлу и файлу, в котором будет результат расшифровки. 

### Пример диалога, в котором жирным шрифтом выделены ответы пользователя: 

1. Для шифрования текста введите 1
2. Для расшифровки текста с помощью ключа введите 2
3. Для расшифровки текста с помощью brute force введите 3
4. Для расшифровки с помощью статистического анализа текста введите 4
5. Для выхода из приложения введите 5

**3**

Расшифровка текста с помощью brute force

Введите путь к файлу, который нужно расшифровать:

**C:\TEMP\EncryptFile\encrypt.txt**

Введите путь к файлу, в котором будет расшифрованный текст:

**C:\TEMP\BrutForce\BrutForceFile.txt**

Расшифровка успешно завершена!



## 3. Расшифровка текста с помощью расшифровка с помощью статистического анализа текста

Для расшифровки текста с помощью  расшифровка с помощью статистического анализа текста необходимо ввести 4.

### Пример диалога, в котором жирным шрифтом выделены ответы пользователя: 

1. Для шифрования текста введите 1
2. Для расшифровки текста с помощью ключа введите 2
3. Для расшифровки текста с помощью brute force введите 3
4. Для расшифровки с помощью статистического анализа текста введите 4
5. Для выхода из приложения введите 5
**4**

Расшифровка с помощью статистического анализа текста

Введите путь к файлу для сбора статистического анализа:

**C:\TEMP\StatAnalisys\BigTextForStatAnalisis.txt**

Введите путь к файлу, который нужно расшифровать:

**C:\TEMP\EncryptFile\encrypted.txt**

Введите путь к файлу, в котором будет расшифрованный текст:

**C:\TEMP\StatAnalisys\DecodedFile.txt**

Расшифровка успешно завершена!


###Результаты выполнения программы в папке TEMP, все названия файлов перечислены в примерах выше.





