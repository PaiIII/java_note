@FunctionalInterface
public interface KiteFunction<T, S, R> {

    /**
     * 定义一个双参数的方法
     *
     * @param t
     * @param s
     * @return
     */
     R run(T t, S s) ;
}
