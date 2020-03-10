package example.moni;
/**
 * @see Strategy
 * 这是减法策略
 */
public class SubStrategy implements Strategy {

    /**
     * 这里是具体的算法（类似于run方法的实现）
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Integer salc(int a, int b) {
        return a - b;
    }
}
