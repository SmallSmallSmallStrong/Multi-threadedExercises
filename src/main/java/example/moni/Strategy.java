package example.moni;

/**
 * 接下来演示一下 设计模式  --- 策略模式
 * 这是java多线程线程使用的设计模式
 * <p>
 * 设计模式分为3个部分：
 * 1.定义抽象策略角色（也就是 Runnble 接口：run方法）
 * 2.定义一个具体的抽象策略角色（Thread：自己实现的run方法，任何实现了 Runnble 接口的类都可以称之为 - 具体的抽象策略角色）
 * 3.环境角色 环境角色类需要持有对 抽象策略角色 的引用,并且调用了 具体的抽象策略角色 的抽象策略角色中的方法
 * 体现在线程中也就是对于run方法的调用 , Thread 类的 start 方法的调用  start方法调用了本地方法 start0 老师说start0 中有对run方法调用具体是不是有我并没有去看
 */
public interface Strategy {
    /**
     * 策略模式体现了面向对象程序设计中非常重要的两个原则：
     * <p>
     * 1 -- 封装变化的概念。
     * 2 -- 编程中使用接口，而不是使用的是具体的实现类(面向接口编程)。
     */
    public Integer salc(int a, int b);


}
