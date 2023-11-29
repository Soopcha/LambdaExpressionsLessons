package StreamAPI;
/*
Определения
● Операция агрегации (aggregation operation) - это операция, которая
вычисляет “значение” из коллекции значений.
(бычно относится к процессу объединения набора значений или данных в одно
 общее значение на основе определенной функции или правила)
● Результатом агрегации может быть одиночное значение, коллекция
 (из 10 тыщ обьектов 10 те 10 агригаций) или ничего (void)
● Stream - это последовательность элементов данных, которая
поддерживает последовательные или параллельные операции агрегации
над ними
● Collection ≠ Stream

Особенности потоков  - стримов - STREAMS
● Потоки не имеют хранилища              (у коллекции есть хранение массивы там и тд)
(похож в этом на итератор)
● Потоки могут представлять бесконечную последовательность элементов
(те длинна не известна заранее)
● Дизайн потоков основан на внутренней итерации
(ичпользуют внут итерацию )
● Потоки предназначены для параллельной обработки без дополнительной работы со
стороны разработчиков
(стримы обрабатывают данные || паралельно с мин усилиями с нашей стороны)
● Потоки предназначены для поддержки функционального программирования
● Потоки поддерживают ленивые  операции
(ленивые операци)
● Потоки могут быть упорядоченными или неупорядоченными
● Потоки нельзя использовать повторно

External Iteration - внешнее итерирование
(фор ич и бежим по массиву, списку какой-то коллекции)
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = 0;
for (int n : numbers) {
    if (n % 2 == 1) {
        int square = n * n;
        sum = sum + square;
    }
}


Internal Iteration - внутреннее итерирование
(тоже самое с помомщью стрим api и коллекция точкв вызов стрим)
int sum = numbers.stream()     //вызов обычного стрима
    .filter(n -> n % 2 == 1)
    .map(n -> n * n)
    .reduce(0, Integer::sum);
int sum = numbers.parallelStream()   // вызов паралельного стрима
    .filter(n -> n % 2 == 1)
    .map(n -> n * n)
    .reduce(0, Integer::sum);



Источник данных для стрим
-массив
-коллекция
-generation function - функция генерации
-IO channel   - input output chenal те из данных из вне можем создать стри


Stream Operations
● Ленивые операции (промежуточные операции) зеленые на фото Intermediate operations (lazy operations)
● Терминальные операции (жадные операции) Terminal operations (eager operations)
(обработка данных стрима начинается после указания терминальной опериции)
(терминальная опреция - ластовая)


Stream Operations ПРИМЕР
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
    .filter(n -> n % 2 == 1)
    .map(n -> n * n)
    .reduce(0, Integer::sum)


Характеристики стрима
- ordered     -упорядоченный (но не отсортированный мб)
-distinct     -содержит уникальные знач
-sorted
-sized        - не бесконечный
-nonnull      -все знач не 0
-immutable    -не изменяемый стрим
-concurrent   -стрим паралельный
-не concurrent


Streams from Values КАК СОЗДАТЬ СТРИМ из обычных знач- Stream.of
    ● static <T> Stream<T> of(T t)
    ● static <T> Stream<T> of(T...values)
Stream<String> stream = Stream.of("Hello");
Stream<String> stream = Stream.of("Ken", "Jeff", "Chris", "Ellen");

int sum = Stream.of(1, 2, 3, 4, 5)
    .filter(n -> n % 2 == 1)
    .map(n -> n * n)
    .reduce(0, Integer::sum);


получили знач для стрима из массива
String[] names = {"Ken", "Jeff", "Chris", "Ellen"};
Stream<String> stream = Stream.of(names);

String str = "Ken,Jeff,Chris,Ellen";
Stream<String> stream = Stream.of(str.split(","));


создаем стрим юзая Builder
Stream.Builder<T> interface
● void accept(T t)
● Stream.Builder<T> add(T t)
● Stream<T> build()
Stream.Builder<String> builder = Stream.builder();

Stream<String> stream = Stream.<String>builder()
.add("Ken")
.add("Jeff")
.add("Chris")
.add("Ellen")
.build();
 */

import StreamAPI.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainApi {
    public static void main(String[] args) {
        //задание стрима
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        Stream<String> stream2 = Stream.of("", "");

        //задание стрима с помощью билдера
        Stream<String> stream3 = Stream.<String>builder()
                .add("Ken")
                .add("Jeff")
                .add("Chris")
                .add("Ellen")
                .build();

        //задание стрима с помощью билдера
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> stream4 = builder.add("Ken")
                .add("Jeff")
                .add("Chris")
                .add("Ellen")
                .build();

        Stream.Builder<String> builder2 = Stream.builder();
        builder2.add("");
        builder2.add("").add("dsd");//c add можем построить цепочку добавлений а с accept нет
        builder2.accept("");
        builder2.accept("");
        builder2.accept("");
        //.accept - ничего не возвращает етрминальный после него мы нечего сделать не можем
        Stream<String> stream7 = builder2.build(); // не забываем про метод построения

        /*
        ещё один варик постраения
        ●       IntStream range(int start, int end)
        ●       IntStream rangeClosed(int start, int end)
        IntStream oneToFive = IntStream.range(1, 6);   // генерируем стрим на интервале от 1 до 6
        IntStream oneToFive = IntStream.rangeClosed(1, 5);  // это закрытый интервал
         */
        IntStream intStream = IntStream.range(1, 9);//обьявили он ещё не робит
        System.out.println("пауза");
        intStream.forEach(x -> {  //мы попали в терминальную операцию и только тут стрим начал работать
            System.out.println(x);
        });

        /*
        Empty Streams не null но пустой стрим полноценный
    Stream<String> stream = Stream.empty();
    IntStream numbers = IntStream.empty();


    Streams from Functions позволяют генерировать обьекты любых типов кастомно
    ● static <T> Stream<T> iterate(T seed, UnaryOperator<T> f) - есть нач знач
    ● static <T> Stream<T> generate(Supplier<T> s)
         */
        System.out.println();
        System.out.println();

/*
    Stream.iterate() method
    ● static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
    seed, f(seed), f(f(seed)), f(f(f(seed)))
    Stream<Long> naturalNumbers = Stream.iterate(1L, n -> n + 1);
    Stream<Long> oddNaturalNumbers = Stream.iterate(1L, n -> n + 2);
 */
        // Stream.iterate() method
        Stream<Long> naturalNumbers = Stream.iterate(1L, n -> n + 1);
        naturalNumbers.limit(5).forEach(x -> System.out.println(x));

        System.out.println();
        System.out.println();


        /*  iterate каждое знач зависит от придыдущего
        Stream.iterate() method
    Stream<Long> tenNaturalNumbers = Stream.iterate(1L, n -> n + 1).limit(10);
    Stream.iterate(1L, n -> n + 2)
        .limit(5)            //limit огр набор знач который будет выполнен или обработан  этой конст 5 тут
        .forEach(System.out::println);
         */

        /* generate - генерирует типы знач но не использует seed не зависит от предыдущ знач
        Stream.generate() method
    ● static <T> Stream<T> generate(Supplier<T> s)
    Stream.generate(Math::random)
        .limit(5)
        .forEach(System.out::println);
    IntStream zeroes = IntStream.generate(() -> 0);
         */


        Stream<Long> tenNaturalNumbers = Stream.iterate(0L, n -> n + 1)
                .limit(10);
        tenNaturalNumbers
                .sorted()
                .limit(5)
                .forEach(x-> System.out.println(x));
        //ну или просто лимит первый должен быть чтобы не поиснуть
        //.limit(5)
        //.sorted()
        System.out.println();
        System.out.println();

        Stream.generate(()->Math.random())
                .limit(5)
                .forEach(System.out::println);


        Stream.generate(()->0)
                .limit(3)
                .forEach(System.out::println);
        System.out.println();
        System.out.println();

        /*
        Получение стрима из масива
        IntStream numbers = Arrays.stream(new int[]{1, 2, 3});
        Stream<String> names = Arrays.stream(new String[] {"Ken", "Jeff"});


        Получение стрима из масива Collection
        Set<String> names = new HashSet<>();
        names.add("Ken");
        names.add("jeff");

        Stream<String> sequentialStream = names.stream();
        Stream<String> parallelStream = names.parallelStream();
         */


        /*

        тип Optional Value

        Optional Value
     Person ken = new Person(1, "Ken", Person.Gender.MALE, null, 6000.0);
     int year = ken.getDob().getYear(); // Throws a NullPointerException если null ken.getDob().getYear() вывело
     System.out.println("Ken was born in the year " + year);

Optional Value create - необязательный тип, обертка вокргу реал знач
● Optional<T> wrapper
○ <T> Optional<T> empty()   - если не оборачивается вокруг чего-то
○ <T> Optional<T> of(T value) throws NPE   - создание обьекта
○ <T> Optional<T> ofNullable(T value)      - создание обьекта

Optional Value create (примеры)
Optional<String> empty = Optional.empty();
Optional<String> str = Optional.of("Hello");
String nullableString = ""; // get a string that may be null...
Optional<String> str2 = Optional.of(nullableString);


Optional Value как получить знач
● T get() throws NPE  // NullPointerExeption
● T orElse(T defaultValue)  //возвращает знач либо дефолтное знач
● T orElseGet(Supplier<? extends T> defaultSupplier)
● <X extends Throwable> T orElseThrow(Supplier<? extends X>
exceptionSupplier) throws X


Optional Value check value  методы для проверки знач
● boolean isPresent()
● void ifPresent(Consumer<? super T> consumer)
         */
        testOpt();
        System.out.println();
        testOpt2();



        /*
        Optional Value
    OptionalInt maxOdd = IntStream.of(10, 20, 30)
                          .filter(n -> n % 2 == 1)
                          .max();
    if (maxOdd.isPresent()) {
        int value = maxOdd.getAsInt();
        System.out.println("Maximum odd integer is " + value);
}
         */
        testOdd3();


        /*
        ОПЕРАЦИИ над стримами

        Debugging a Stream Pipeline
        peek - отладочная операция, делает стрим более подходящим для дебага

        int sum = Stream.of(1, 2, 3, 4, 5)
        .peek(e -> System.out.println("Taking integer: " + e))
        .filter(n -> n % 2 == 1)
        .peek(e -> System.out.println("Filtered integer: " + e))
        .map(n -> n * n)
        .peek(e -> System.out.println("Mapped integer: " + e))
        .reduce(0, Integer::sum);



        Stream ForEach- выполняет действие с каждым эл стрима
• void forEach(Consumer<? super T> action)
• void forEachOrdered(Consumer<? super T> action)
-поддерживает порядок добавления


Stream forEach operation - фор ич терминальная (ласт ) операция

List<Person> persons = ….
persons.stream()
    .filter(Person::isFemale)
    .forEach(p -> p.setIncome(p.getIncome() * 1.10));


    Stream map operation - не мапа, преобразование обьектов

    Stream map operation - не терминальная, а интермидия операция
• <R> Stream<R> map(Function<? super T,? extends R> mapper)
• DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
• IntStream mapToInt(ToIntFunction<? super T> mapper)
• LongStream mapToLong(ToLongFunction<? super T> mapper)

Stream map operation  пример
IntStream.rangeClosed(1, 5)
    .map(n -> n * n)
    .forEach(System.out::println);

personsList()
    .stream()
    .map(Person::getName)
    .forEach(System.out::println);
         */
        testMap();
    }

    public static void testOpt(){
        Optional<String> str = Optional.of("Hello");
        if (str.isPresent()) {//проверяем есть ли значение
            String value = str.get();
            System.out.println("Optional contains " + value);
        }
        else { System.out.println("Optional is empty.");}
    }

    public static void testOpt2(){
        Optional<String> str = Optional.of("Hello");
        str.ifPresentOrElse(x -> System.out.println(x), ()-> System.out.println("Str is empty"));

        Optional<String> str2 = Optional.ofNullable(null);
        str2.ifPresentOrElse(x -> System.out.println(x), ()-> System.out.println("Str is empty"));
    }

    public static void testOdd3() {
        OptionalInt maxOdd = IntStream.of(10, 20, 30)
                .filter(n -> n % 2 == 1) //ищем чтобы ост от деления на 2 = 1
                .max();
        if (maxOdd.isPresent()) {
            int value = maxOdd.getAsInt();
            System.out.println("Maximum odd integer is " + value);
        } else {
            System.out.println("No Maximum odd integer");
        }
    }

   /* public static void testOdd4() {
        Integer maxOdd = Stream.of(new Integer(11), new Integer(20),new Integer(30))
                .filter(n -> n % 2 == 1) //ищем чтобы ост от деления на 2 = 1
                .findFirst()
                .orElse(null);

        if (maxOdd != null) {
            int value = maxOdd.getAsInt();
            System.out.println("Maximum odd integer is " + value);
        } else {
            System.out.println("No Maximum odd integer");
        }
    }

    */

    public static void testMap(){
        Stream<Person> personStream = Stream.of(new Person("abc",20), new Person("sds",30));
        Stream<String> mapStream = personStream.map(x -> {
           return x.getName();
        }); //мапой преобразовали в стрим строк
        mapStream.forEach(x -> {
            System.out.println(x);
        });
    }
}
