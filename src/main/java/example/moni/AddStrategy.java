package example.moni;

/**
 * 这里是加法策略
 * @see example.moni.Strategy
 */
public class AddStrategy implements Strategy {

    /**
     * 这里是具体的算法（类似于run方法的实现）
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Integer salc(int a, int b) {
        return a + b;
    }
}
