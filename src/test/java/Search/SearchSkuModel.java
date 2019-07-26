package Search;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class SearchSkuModel extends SearchModel {

    // / <summary>
    // / �޸���ʼʱ��
    // / </summary>
    @JsonSerialize(using = Models.CustomDateSerializer.class)
    public Date modified_begin = null;

    // / <summary>
    // / �޸Ľ���ʱ��
    // / </summary>
    public Date modified_end;

    // / <summary>
    // / ��Ʒ���룬����ö��ŷָ�
    // / </summary>
    public String sku_ids;

}
