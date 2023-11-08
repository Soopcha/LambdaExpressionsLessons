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



 */



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}