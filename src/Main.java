/*
ЛЯМБДА ВЫРАЖЕНИЯ

Functional Interface
● Функциональный интерфейс - это интерфейс,
который имеет только один абстрактный метод
(но др методы дефолт и статик может иметь без ограничения )
 и имеет анотацию @FunctionalInterface- чтобы не забыть что он функциональный то
 так что надо писать её.
● Функциональный интерфейс содержит определение
одной операции в виде абстрактного метода

Functional Interface
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}

@FunctionalInterface
public interface Operations {
    double add(double n1, double n2); //Compilation Error
    double subtract(double n1, double n2); //Compilation Error
}





● Лямбда-выражение - это безымянная функция,
используемая в функциональном программировании
● Лямбда выражение - это анонимная реализация
функционального интерфейса

Syntax   в  скобках (входной аргумент)
(int x) -> x + 1
(int x, int y) -> x + y
(int x, int y) -> {
    int max = x > y ? x : y;
    return max;
}
() -> { }

lambda expression - лямбда выражение (LE)
● LE НЕ МЕТОД
● LE - это экземпляр реализации функционального
интерфейса
● LE - не имеет имени  (анонимный же)
● LE - не имеет return-типа, тип выводится из
контекста(компилятором)
● LE - не имеет предложения throws (try catch писать если могут быть ошибки)
● LE - не может иметь параметров типа - не
может быть Generic

чем лямда функии отличается от анонимного лкасса?
АНОНИМНЫЙ КЛАСС:
(пример ниже в мейне и в ламда тест)

Лямбда и анон клас:
Общее:
1 лок переменный могут юзаться только если они final или effective final
2 разрешает доступ к переменным класса и статич переменным класса
3 они не должны выбрасывать больше исключений чем определено в trows метода функционального интерфейса
Различия:
1 для анон классов this ссылается на сам класс, а в лямбде this указывает
на тот класс, в котором и реализуется ламбда выражение
2 Анон классы компилируются во внутренние класы. Лямбда выражения
преобразуются в статические прайват методы класса в котором они юзают
invokedynamic инструкцию
3 те ламбда менее ресурсотребуемое



Стандартные функциональные интерфейсы:
java.util.function
● Function<T,R> - R apply(T t)
    @FunctionalInterface
    public interface Function<T,R>{
        R apply(T t)
    }

    Finction<Integer, String> convert = x.valueOf(x)+"долларов";
    System.out.println(convert.apply(5)); //5 доларов

есть дефолтный метод
default <V> Function<V,R> compose(Function<? super V,? extends T>
before)
позволяет комбинировать лямда выражения

есть ещё addThen он в другом порядке совмещает

identity - единичный метод статик - единичное лямда выражение
превращает выражение само в себя   оно ничего не делает это выражение
● BiFunction<T, U, R> - R apply(T t, U u)
● Predicate<T> - boolean test(T t)
проверяет да нет

● BiPredicate<T, U> - boolean test(T t, U u)
● Consumer<T> - void accept(T t)
потребитель
возвращает только void

● BiConsumer<T, U>
● Supplier<T> - T get()
поставщик
ничего не принимает но что-то возвращает

● UnaryOperator<T> - T apply(T t)
унарный оператор те функция   f(x) = y

● BinaryOperator<T> - T apply(T t1, T t2)


би - тоже самое но принимают два оператора



Область видимости эл в лямда выражениях и лок классах
переменную в лямда выр называть не как локальную, а то локальные же видно


Variable Capture  захват переменной
● declared final
● not declared final, but initialized only once

public Printer test() {
    final String msg = "Hello"; // msg is effectively final
    Printer printer = msg1 -> System.out.println(msg + " " + msg1);
    return printer;
}
те любые переменный в лямда выр должны быть файнал (или фикивный файнал где она по факту не меняется
 тк где-то обявили ламда выр а где его заюзаем никто не знает никогда , поэтому и файнал чтобы не менялось
 вводимое значение)
 и в лямде выр нельзя менять переменные тоже (даже если фиктивный файнал  те без final но во всём коде не меняется)
 но например поле можно менять сколько хоти хехе


jumps and exit - выходы из лямда выражений

рекурсивные ламда выр есть






 */



public class Main {
    private static String ass = "хехехех";
    public static void main(String[] args) {

        int a = 0;

        //это анонимный класс:  приммер
        // те нет имени но интерфейс реализует
        Runnable action = new Runnable() {
            @Override
            public void run() {
                if (a == 0) { //есть доступ к переменным тоесть
                    System.out.println("/////"+ass);
                }
            }
        };

        //тоже самое можно написать с помомщью лямбда выражений:
        Runnable action2 = () -> {
            System.out.println("....");
        };
        // единственный минус: с помощью ламда выражений нельзя реализовать
        // интерфейс где больше одного метода




        PredicateTest.test();
        System.out.println("  -----  ");
        PredicateTest.test2();
    }
}