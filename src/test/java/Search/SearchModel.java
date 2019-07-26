package Search;

import java.io.Serializable;

public class SearchModel implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -312270604484284028L;

    // / <summary>
    // / ���̱��
    // / </summary>
    public int shop_id;

    public int page_index = 1;

    // / <summary>
    // / ��ѯ����,Ĭ�� 30
    // / </summary>
    public int page_size = 30;

    // / <summary>
    // / �ֶ����������Ӣ�Ķ��ŷָ�
    // / </summary>
    public String flds = "*";
}
