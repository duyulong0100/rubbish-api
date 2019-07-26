package Api;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class OpenApi {

    // / <summary>
    // / �������ʱ�ģ�������
    // / </summary>
    protected String RequestName;

    // / <summary>
    // / ������ID
    // / </summary>
    protected String PartnerId;

    // / <summary>
    // / ������KEy
    // / </summary>
    protected String PartnerKey;

    public String QueryData(Models.JobModel jobModel) throws IOException {
        String sRes = "";
        String sUrl = "";
        HashMap<String, String> dicArg = new HashMap<String, String>();
        sUrl = GetRequestUrl(PartnerKey, dicArg);
        sRes = PostData(sUrl, jobModel.getData());
        return sRes;
    }

    // / <summary>
    // / ��ʼ��
    // / </summary>
    protected void Init(HashMap<String, String> dicArg) {

        if (dicArg.get("ts") == null) {
            dicArg.put("ts", String.valueOf(Utility.GetStamp(new Date())));

        }
        dicArg.put("partnerid", PartnerId);
        dicArg.put("method", RequestName);
        dicArg.put("token", Token);
    }

    protected String HostUrl;

    // / <summary>
    // / ǩ���ַ���
    // / </summary>
    // / <returns>ǩ�����</returns>
    protected String DoMD5(String prestr) {
        return Utility.DoMD5(prestr, null);
    }

    // / <summary>
    // / ��ȡǩ��
    // / </summary>
    // / <returns></returns>
    protected String GetSign(String sPartnerId, String sPartnerKey, HashMap<String, String> dicArg) {
        String sSignPara = GetSignPara(dicArg);
        String sSign = this.RequestName + sPartnerId + sSignPara + sPartnerKey;
        return DoMD5(sSign);
    }

    protected String GetSignPara(HashMap<String, String> dicArg) {
        StringBuilder prestr = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = dicArg.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            if (key == "sign" || key == "method" || key == "partnerid")
                continue;
            prestr.append(entry.getKey() + entry.getValue());
        }
        return prestr.toString();
    }

    // / <summary>
    // / POST����
    // / </summary>
    // / <param name="sContent"></param>
    // / <returns></returns>
    protected String PostData(String sUrl, String sContent) throws IOException {
        if (sContent == null || sContent.isEmpty()) {
            return Utility.PostData(sUrl, "");
        } else {
            return Utility.PostData(sUrl, sContent);
        }
    }

    public String GetRequestUrl(String sPartnerKey, HashMap<String, String> dicArg) throws IOException {
        Init(dicArg);

        String sSignPara = GetSignPara(dicArg);
        String sPartnerId = dicArg.get("partnerid");
        String sMethod = dicArg.get("method");
        String sSign = sMethod + sPartnerId + sSignPara + sPartnerKey;
        sSign = DoMD5(sSign);
        dicArg.put("sign", sSign);
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(HostUrl);

        Boolean isFirst = true;
        Iterator<Map.Entry<String, String>> iterator = dicArg.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (isFirst) {
                sbUrl.append("?");
            } else {
                sbUrl.append("&");
            }
            sbUrl.append(entry.getKey());
            sbUrl.append("=");
            sbUrl.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            isFirst = false;
        }
        return sbUrl.toString();
    }

    public String Token;

    protected void AddArg(HashMap<String, String> dicArg, String sKey, String sValue) {
        dicArg.put(sKey, sValue);
    }
}
