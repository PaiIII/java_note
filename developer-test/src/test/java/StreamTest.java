import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    private static void test4() {
        Stream<String> a = Stream.of("a1", "c2", "b3");
        //a.sorted().forEach(e->System.out.println(e));
        a.sorted((x, y) -> Integer.parseInt(x.substring(1)) > Integer.parseInt(y.substring(1)) ? 1 : -1).forEach(e -> System.out.println(e));
    }


    private static void test3() {
        Stream<String> a = Stream.of("a", "b", "c", "D");
        List<String> b = new ArrayList<>();
        a.forEach(e -> {
            b.add(e.toUpperCase());
        });
        System.out.println(b.toString());
    }

    private static void test2() {
        Stream<Integer> a = Stream.of(500, 100, 50, 10);
        Optional o = a.max(Integer::compareTo);
        Integer r = (Integer) o.get();
        System.out.println(r);

        Stream<Integer> b = Stream.of(100, 50, 10);
        System.out.println(b.min(Integer::compareTo).get());

        Stream<Integer> c = Stream.of(100, 50, 10, 100);
        Comparator<Integer> t = (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1);
        System.out.println(c.max(t).get());

        Stream<Integer> d = Stream.of(20, 100, 50, 10, 100);
        System.out.println(d.findFirst().get());
        Stream<Integer> e = Stream.of(20, 100, 50, 10, 100);
        System.out.println(e.findAny().get());
    }

    private static void test1() {
        Stream<String> a = Stream.of("a", "b", "c");
        //a = Stream.empty();
        //System.out.println(a.count());
        Stream<String> b = Stream.of("a", "d", "e");
        Stream<String> c = Stream.concat(a, b);
        System.out.println(c.count());
    }

}
