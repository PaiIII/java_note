/*

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamMainTest {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    private static void test4() {
        System.out.println(new HashMap<String, Object>().put("a", "b"));
        System.out.println(new HashMap<String, String>() {
            {
                put("name", "test");
                put("age", "20");
            }
        });
    }

    private static void test3() {
        List<User> lst1 = getUserData();
        List<User> lst2 = getUserData();
        List<List<User>> lst3 = new ArrayList<>();
        lst3.add(lst1);
        lst3.add(lst2);
        lst3.stream().map(users -> users.stream().map(user -> {
            user.setPassword("123");
            return user;
        })).collect(Collectors.toList());
        System.out.println("test3~~~" + lst3);
    }

    private static void test2() {
        List<User> lst1 = getUserData();
        List<User> lst2 = getUserData();
        List<List<User>> lst3 = new ArrayList<>();
        lst3.add(lst1);
        lst3.add(lst2);
        lst3.stream().flatMap(users -> users.stream().map(user -> {
            user.setPassword("123");
            return user;
        })).collect(Collectors.toList());
        System.out.println("test2~~~" + lst3);
    }

    private static void test1() {
        List<User> lst = getUserData().stream().filter(u -> u.getAppAuthority() > 50 && u.getSex() == 0).collect(Collectors.toList());
        lst.forEach(u -> System.out.println(u));
        List<String> lst1 = getUserData().stream().map(User::getId).collect(Collectors.toList());
        List<String> lst3 = getUserData().stream().map(u -> {
            return u.getId();
        }).collect(Collectors.toList());

        System.out.println(lst1);
        System.out.println("lst3~~~~~~" + lst3);

        List<User> lst2 = getUserData().stream().map(u -> {
            u.setPassword("123");
            return u;
        }).collect(Collectors.toList());
        System.out.println(lst2);

    }

    private static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName(String.format("古时的风筝 %s 号", i));
            user.setAppAuthority(random.nextInt(100));//年龄
            user.setSex(i % 2);
            user.setPhone("18812021111");
            user.setAccount("无");
            users.add(user);
        }
        return users;
    }
}
*/
