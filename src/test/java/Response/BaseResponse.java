package Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements java.io.Serializable {
    // / <summary>
    // / ������
    // / </summary>
    public int code;

    // / <summary>
    // / �Ƿ��쳣��Ŀǰ������ code=0 ������쳣
    // / </summary>
    public Boolean issuccess;

    // / <summary>
    // / ��Ϣ
    // / </summary>
    public String msg;
}
