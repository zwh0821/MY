package context;

import inter.Strategy;

/**
 * @program: CommunicationTest
 * @description: 上下文
 * @author: zhuowh
 * @create: 2021-06-28 15:10
 **/
public class Context {

    private Strategy strategy;

    public void startListening() throws Exception {
        strategy.startListening();
    }

    public void closeListening() throws Exception {
        strategy.closeListening();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
