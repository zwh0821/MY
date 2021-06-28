package inter;

/**
 * @program: CommunicationTest
 * @description: 策略类
 * @author: zhuowh
 * @create: 2021-06-28 15:01
 **/
public interface Strategy {


    void startListening() throws Exception;

    void closeListening() throws Exception;

}
