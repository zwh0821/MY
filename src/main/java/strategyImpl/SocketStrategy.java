package strategyImpl;

import inter.Strategy;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: CommunicationTest
 * @description: socket通信策略实现类
 * @author: zhuowh
 * @create: 2021-06-28 15:15
 **/
public class SocketStrategy implements Strategy {

    boolean isOpen = false;
    private static final String HOST = "192.168.4.1";
    private static final int PORT = 10001;
    private static final String order = "8C 01 01 05 05";

    @Override
    public void startListening() throws Exception {
        try {
            Socket socket = new Socket(HOST, PORT);
            this.isOpen = true;
            //得到一个输入流，用于接收服务器响应的数据
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            System.out.println("客户端IP地址:" + socket.getInetAddress().getHostAddress());
            String info = "";
            byte[] buffer = new byte[8];
            int len = 0;
            while (-1 != (len = bufferedInputStream.read(buffer))) {
                Map<Integer, Object> data = DataProcess.handleData(buffer);
                System.out.println(DataProcess.handleData(buffer));
            }
            socket.shutdownInput();
        } catch (UnknownHostException e) {
            throw new Exception("未找到对应端口");
        } catch (IOException e) {
            throw new Exception("监听端口失败:" + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void closeListening() throws Exception {
        this.isOpen = false;
    }

}
