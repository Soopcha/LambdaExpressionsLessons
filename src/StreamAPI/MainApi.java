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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainApi {
    public static void main(String[] args) {
        //задание стрима
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        Stream<String> stream2 = Stream.of("","");

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
        IntStream intStream = IntStream.range(1,9);
        System.out.println("пауза");
        intStream.forEach(x ->{  //мы попали в терминальную операцию и только тут стрим начал работать
            System.out.println(x);
        });
    }
}