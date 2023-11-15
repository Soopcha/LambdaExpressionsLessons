import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args){
        Function<Integer,Integer> multiply = (value) -> value * 2;
        Function<Integer,Integer> add = (value) -> value +3;

        Function<Integer,Integer> addThenMult = multiply.compose(add);

        Integer res = addThenMult.apply(3);
        System.out.println(res);
    }
}
