package Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

public class Utility {

    public static int GetStamp(Date d) {

        return (int) (d.getTime() / 1000);

    }

    /**
     * ����
     */
    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    /**
     * �ַ�����д
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    /**
     * byteToHesString
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5����
     * @param origin
     * @param charsetname
     * @return
     */
    public static String DoMD5(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {
            resultString = null;
        }
        return resultString;
    }

    public static String PostData(String sUrl, String sContent) throws IOException {

        // Post�����url����get��ͬ���ǲ���Ҫ������
        URL postUrl = new URL(sUrl);
        // ������
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

        // �����Ƿ���connection�������Ϊ�����post���󣬲���Ҫ����
        // http�����ڣ������Ҫ��Ϊtrue
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // Ĭ���� GET��ʽ
        connection.setRequestMethod("POST");

        // Post ������ʹ�û���
        connection.setUseCaches(false);

        connection.setInstanceFollowRedirects(true);

        // ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
        // ��˼��������urlencoded�������form�������������ǿ��Կ������Ƕ���������ʹ��URLEncoder.encode
        // ���б���
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        // ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
        // Ҫע�����connection.getOutputStream�������Ľ���connect��
        connection.connect();

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // ���ģ�����������ʵ��get��URL�� '? '��Ĳ����ַ���һ��
        out.write(sContent.getBytes("utf-8"));
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line;
        String sResult = "";
        while ((line = reader.readLine()) != null) {
            sResult += line;
        }

        reader.close();
        connection.disconnect();
        return sResult;
    }

    private static ObjectMapper mapper = new ObjectMapper();

    // ���л�
    public static String Serialize(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    // �����л�Ϊʵ�����
    public static <T> T DeSerialize(String sData, Class<T> obj) throws JsonMappingException, IOException {
        return mapper.readValue(sData, obj);
    }

    // �����л�Ϊʵ���б����
    public static <T> T DeSerialize(String sData, TypeReference<T> obj) throws JsonMappingException, IOException {
        return mapper.readValue(sData, obj);
    }
}
