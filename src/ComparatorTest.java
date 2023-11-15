/*
Компораторы
Comparing Objects
@FunctionalInterface
public interface Comparator<T> {
int compare(T o1, T o2);
...
}


Comparing Objects
● static <T,U extends Comparable<? super U>>Comparator<T>
comparing(Function<? super T,? extends U> keyExtractor)
● default <U extends Comparable<? super U>>Comparator<T>
thenComparing(Function<? super T,? extends U> keyExtractor)

Comparator<Person> firstNameComp = Comparator.comparing(Person::getFirstName);
Comparator<Person> lastFirstDobComp =
    Comparator.comparing(Person::getLastName)
    .thenComparing(Person::getFirstName)
    .thenComparing(Person::getDob);


 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    public static void test(){//сортирует по имени потом по возрасту
        Comparator<Person> otherComp =
                Comparator.comparing(Person::getName)
                        .thenComparing(Person::getAge)
                        .thenComparing(Person::getBool);

        List<Person> list = new ArrayList<>();
        list.sort(otherComp);
        Collections.sort(list, otherComp);
    }

}
