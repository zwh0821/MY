package strategyImpl;

import inter.Strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * @program: CommunicationTest
 * @description: socket通信策略实现类
 * @author: zhuowh
 * @create: 2021-06-28 15:15
 **/
public class SocketStrategy implements Strategy {

    boolean isOpen = false;
    private static final String HOST="192.168.4.1";
    private static final int PORT=10001;
    private static final String order = "8C 01 01 05 05";

    @Override
    public void startListening() throws Exception {
        try {
            Socket socket  = new Socket(HOST, PORT);
            this.isOpen = true;
            InputStream is;
            OutputStream os;
            while (true){
                is = socket.getInputStream();
                os = socket.getOutputStream();
                if(isOpen){
                    os.write(order.getBytes(StandardCharsets.UTF_8));
                    byte[] datas = new byte[1024];
                    is.read(datas);
                    //处理数据

                    //渲染界面

                }else{
                    is.close();
                    os.close();
                    socket.close();
                }
            }
        }
        catch (UnknownHostException e) {
            throw new Exception("未找到对应端口");
        } catch (IOException e) {
            throw new Exception("监听端口失败:"+e.getMessage());
        }

    }

    @Override
    public void closeListening() throws Exception {
        this.isOpen = false;
    }

}
