package strategyImpl;

import helper.AESCipherUtils;
import helper.ParseSystemUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: CommunicationTest
 * @description:
 * @author: zhuowh
 * @create: 2021-06-28 18:36
 **/
public class DataProcess {

    public static Map<Integer, Object> handleData(byte[] bytes) throws Exception { String hexStr = AESCipherUtils.bytesToHexStr(bytes);
        System.out.println(hexStr);
        //解析数据
        //编号
        String code = hexStr.substring(0, 2);
        //功能码
        String functionCode = hexStr.substring(2, 4);
        //数据
        String data = hexStr.substring(6, 10);
        //校验码
        String checkCode = hexStr.substring(14);

        //校验数据正确性校验和：
        //数据部分相加的和，取低8位。
        int checkNum1 = Integer.valueOf(data.substring(1, 2), 16);
        int checkNum2 = Integer.valueOf(data.substring(3), 16);
        int checkNum = Integer.valueOf(checkCode,16);
        if (checkNum1 + checkNum2 == checkNum) {
            return processData(data);
        } else {
            return null;
        }
    }

    public static Map<Integer, Object> processData(String data) throws Exception {

        Map<Integer, Object> result = new HashMap<>();
        //解析data
        String bytes_01 = AESCipherUtils.hexToBin(data.substring(0, 2));
        if (bytes_01.length() == 8) {
            for (int i = 0; i < bytes_01.length(); i++) {
                result.put(i, bytes_01.substring(i,i+1));
            }
        } else {
            throw new Exception("无效的数据");
        }
        String bytes_23 = AESCipherUtils.hexToBin(data.substring(2));
        if (bytes_23.length() == 8) {
            for (int i = 0; i < bytes_23.length(); i++) {
                result.put(i+8, bytes_23.substring(i,i+1));
            }
        } else {
            throw new Exception("无效的数据");
        }
        return result;
    }
}
