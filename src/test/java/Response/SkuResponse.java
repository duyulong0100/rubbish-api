package Response;

import java.util.List;

public class SkuResponse extends BaseResponse {

    // / <summary>
    // / ��ѯ���ݸ���
    // / </summary>
    public int page_size;

    // / <summary>
    // / �ڼ�ҳ
    // / </summary>
    public int page_index;

    // / <summary>
    // / �Ƿ�����һҳ
    // / </summary>
    public Boolean has_next;

    public List<SkuModel> datas;

}
