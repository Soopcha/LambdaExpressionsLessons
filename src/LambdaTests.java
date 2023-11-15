import java.io.IOException;

public class LambdaTests {
    public static void test1() throws IOException {
        Adder adder = (x, y) -> x + y; //те x + y - это реализация метода из интерфейса
        //или Adder adder = Double::sum;
        //обьявили лямбда выражение и присвоили его к интерфейсу
        //присвоили переменной этого интерфейса ссылку на экземпляр функционального интерфейса
        //компилятор возбмет обьявление и на основе  x + y сгинирирует что-то типо анонимного класса
        /*
        Анонимный класс в Java - это конструкция, позволяющая создавать классы "на лету" без явного
         объявления имени класса. Они наиболее полезны там, где нужно реализовать небольшие одноразовые
         классы с небольшим объемом кода. ПРИМЕР
         interface Greeting {
    void greet();
}

public class Example {
    public static void main(String[] args) {
        Greeting anonymousGreeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("Hello, World!");
            }
        };

        anonymousGreeting.greet();
    }
}

В этом примере Greeting - это интерфейс с одним методом greet().
Анонимный класс реализует этот интерфейс непосредственно в блоке кода,
создавая объект anonymousGreeting. Этот объект может быть использован
для вызова метода greet(), который выводит "Hello, World!".
         */

        Joiner joiner = (x, y) -> x + y;
        double sum1 = adder.add(10.34, 89.11);
        double sum2 = adder.add(10, 89);
        String str = joiner.join("Hello", " lambda");
        test(adder);
        //test((x, y) -> x + y);//или так но тут ошибка тк не ясно какой метод вызывают

        //так вот можно исправить
        test((double x, double y)->x+y);
        test((Adder) (x, y) -> x + y);

    }

    public static void test(Adder adder) throws IOException {//IOException по фану показать сроусы
        double x = 190.90;
        double y = 8.50;
        double sum = adder.add(x, y);
        System.out.println(x + " + " + y + " = " + sum);

    }

    public static void test(Joiner joiner) {
    }

}
