/*
КАк ещё можно записать лямда выр --
это синтаксичесский сахар
Method Reference <Qualifier>::<MethodName>
    ● Ссылка на метод это НЕ новый тип в Java, это НЕ указатель на
функцию
    ● Ссылка на метод - это упрощенный синтаксис для записи лямбда
выражения, в котором используется существующий метод

ToIntFunction<String> lengthFunction = str -> str.length();
String name = "Ellen";
int len = lengthFunction.applyAsInt(name);

ToIntFunction<String> lengthFunction = String::length;
String name = "Ellen";
int len = lengthFunction.applyAsInt(name);




Static Method References
● static String toBinaryString(int i)

// Using a lambda expression
Function<Integer, String> func1 = x -> Integer.toBinaryString(x);
System.out.println(func1.apply(17));

// Using a method reference
Function<Integer, String> func2 = Integer::toBinaryString;
System.out.println(func2.apply(17));


Static Method References (тут надо би фанкшс а то тут ниже не верно)
● static int sum(int a, int b)
Function<Integer, Integer> func2 = Integer::sum; // A compile-time error

// Uses a lambda expression
BiFunction<Integer, Integer, Integer> func1 = (x, y) -> Integer.sum(x, y);
System.out.println(func1.apply(17, 15));

// Uses a method reference
BiFunction<Integer, Integer, Integer> func2 = Integer::sum;
System.out.println(func2.apply(17, 15));


Instance Method References
● objectRef::instanceMethod / Bound Receiver
Supplier<Integer> supplier = () -> "Ellen".length();
System.out.println(supplier.get());
Supplier<Integer> supplier = "Ellen"::length;
System.out.println(supplier.get());

Consumer<String> consumer = str -> System.out.println(str);
consumer.accept("Hello");
Consumer<String> consumer = System.out::println;
consumer.accept("Hello");



Constructor References
● ClassName::new
● ArrayTypeName::new

Supplier<String> func1 = () -> new String();
Function<String,String> func2 = str -> new String(str);

Supplier<String> func1 = String::new;
Function<String,String> func2 = String::new;


Supplier<Item> func1 = () -> new Item();
Function<String,Item> func2 = name -> new Item(name);
BiFunction<String,Double, Item> func3 = (name, price) -> new Item(name,
price);

Supplier<Item> func1 = Item::new;
Function<String,Item> func2 = Item::new;
BiFunction<String,Double, Item> func3 = Item::new;
компилятор сам решает какой конструктор лучше вызвать
 */

import java.util.function.ToIntFunction;

public class MethodReferenceEx {
    public static void test(){
        ToIntFunction<String> lengthFunction = String::length; //str -> str.length(); то же
        String name = "Ellen";
        int len = lengthFunction.applyAsInt(name);
    }
}
