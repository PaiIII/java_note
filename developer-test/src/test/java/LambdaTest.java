import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class LambdaTest {
    public static void main(String[] args) {
        //test();
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        boolean t = false;
        System.out.println(!t);
    }

    //lambda表达式
    private static void test5() {
        KiteFunction<LocalDateTime, String, String> t = (localDateTime, s) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
            return localDateTime.format(dateTimeFormatter);
        };
        String dateString = t.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString);
    }

    //匿名内部类
    private static void test4() {
        String dateString = new KiteFunction<LocalDateTime, String, String>() {
            @Override
            public String run(LocalDateTime localDateTime, String s) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
                return localDateTime.format(dateTimeFormatter);
            }
        }.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString);
    }

    //有FunctionTest类
    private static void test3() {
        KiteFunction<LocalDateTime, String, String> functionDateFormat = FunctionTest::DateFormat;
        String dateString = functionDateFormat.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString);
    }

    private static void test2() {
        Function<String, Integer> s = Integer::parseInt;
        Integer i = s.apply("10");
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(1000, 100));
        IntBinaryOperator intBinaryOperator = Integer::compare;
        System.out.println(intBinaryOperator.applyAsInt(10, 100));
    }


    private static void test() {
        List<String> names = new ArrayList<>();
        names.add("B");
        names.add("A");
        names.add("C");
        System.out.println(names.toString());
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println(names.toString());
    }

    private static void test1() {
        Test1 test1 = () -> 2;
        System.out.println(test1.run());

        MathOperation mathOperation = (a, b) -> a + b;
        System.out.println(LambdaTest.operate(10, 5, mathOperation));

        MathOperation multiplication = (a, b) -> {
            return a + b;
        };
        System.out.println(LambdaTest.operate(10, 5, multiplication));
    }

    interface Test1 {
        int run();
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
