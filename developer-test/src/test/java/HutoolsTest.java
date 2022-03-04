import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class HutoolsTest {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        test6();
    }

    private static void test6() {
        String str = StrUtil.format("%s:%s", "key", "value");
        String str2 = String.format("%s:%s", "key", "value");
        System.out.println(str);
        System.out.println(str2);
    }

    private static void test5() {
        String content = "test中文";
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, "abcd157894612312".getBytes());
        String encryptHex = aes.encryptHex(content);
        Console.log(encryptHex);
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        Console.log(decryptStr);
    }

    private static void test4() {
        long[] b = {1, 2, 3, 4, 5};
        String bStr = Convert.toStr(b);
        System.out.println(bStr);
        String[] c = {"1", "2", "3", "4"};
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(c);
        System.out.println(intArray);

        System.out.println(IdUtil.simpleUUID());
    }

    private static void test3() {
        String originalFilename = "台打test.test///111.txt";
        System.out.println(StrUtil.subBetween(originalFilename, "test.", ".txt"));
        System.out.println(StrUtil.subBefore(originalFilename, ".", true));
        String fileName = StrUtil.subBefore(originalFilename, ".", true) + StrUtil.UNDERLINE + DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss") + originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName2 = StrUtil.subBefore(originalFilename, StrUtil.DOT, true) + StrUtil.UNDERLINE + DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss") + StrUtil.DOT + StrUtil.subAfter(originalFilename, StrUtil.DOT, true);
        System.out.println(fileName);
        System.out.println(fileName2);
        String fileUrl = "/三相汇控箱_1635416080997/das.jpg";
        String a = StrUtil.subAfter(fileUrl, StrUtil.SLASH, false);
        System.out.println(a);
    }

    private static void test2() {
        String originalFilename = "台打test.test111.txt";
        System.out.println(StrUtil.sub(originalFilename, 0, originalFilename.lastIndexOf(".")));
        System.out.println(StrUtil.str("aaa", Charset.defaultCharset()));
        System.out.println(StrUtil.bytes("aaa", Charset.defaultCharset()));
        String template = "{}爱{}，{}就像老鼠爱大米";
        System.out.println(StrUtil.format(template, "我", "你", "我"));
        System.out.println(StrUtil.format(template, "我", "你"));

        String format = DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss");
        System.out.println(format);
        System.out.println(format);
    }

    private static void test1() {
        String a = " ";
        String b = "B";
        String c = "C";
        System.out.println(StrUtil.isBlank(a));
        System.out.println(StrUtil.isEmpty(a));
        System.out.println(StrUtil.hasBlank(a, b, c));
        System.out.println(StrUtil.hasEmpty(a, b, c));
    }
}
