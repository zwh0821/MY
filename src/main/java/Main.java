import context.Context;
import strategyImpl.SerialPortStrategy;
import strategyImpl.SocketStrategy;

/**
 * @program: CommunicationTest
 * @description: 主线程
 * @author: zhuowh
 * @create: 2021-06-28 14:58
 **/
public class Main {

    private final static String type = "socket";

    public static void main(String[] args) {

        Context context = new Context();
        if (type.equals("socket")) {
            context.setStrategy(new SocketStrategy());
        } else if (type.equals("serialPort")) {
            context.setStrategy(new SerialPortStrategy());
        }
        try {
            context.startListening();
            Thread.sleep(1000*60);
            context.closeListening();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
