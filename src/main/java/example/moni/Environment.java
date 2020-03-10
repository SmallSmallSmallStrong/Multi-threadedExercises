package example.moni;

/**
 * 这里是使用的的时候的调用
 *
 * @see Strategy
 */
public class Environment {
    //持有对策略对象的引用
    private Strategy strategy;

    public Environment(Strategy strategy) {
        this.strategy = strategy;
    }

    public int salc(int a, int b) {
        return strategy.salc(a, b);
    }
}
