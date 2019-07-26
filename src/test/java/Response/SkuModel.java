package Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuModel implements java.io.Serializable {

    // / <summary><br/>
    // / SKU��ţ��40��һ���̼Ҵ˱���Ψһ; For example:A2821003006
    // / </summary>
    public String sku_id;

    // / <summary>
    // / ����SKU��ţ��20
    // / </summary>
    public String shop_sku_id;

    // / <summary><br/>
    // / ��Ʒ��ţ�һ���̼Ҵ˱���Ψһ���20; For example:10090462
    // / </summary>
    public String i_id;

    // / <summary>
    // / �ⲿ��ţ���Ӧ��Ʒ��ŵı������ 20
    // / </summary>
    public String shop_i_id;

    // / <summary><br/>
    // / ��Ʒ���ƣ��100; For example:ţƤ �����
    // / </summary>
    public String name;

    // / <summary><br/>
    // / ���ۼۣ���λ���֣�; For example:10.28
    // / </summary>
    public BigDecimal sale_price;

    // / <summary><br/>
    // / �������; For example:20
    // / </summary>
    public int shop_qty;

    // / <summary><br/>
    // / ��ɫ���100; For example:����ɫ
    // / </summary>
    public String color;

    // / <summary><br/>
    // / �������ƣ��; For example:1627207:28341:��ɫ����:��ɫ
    // / </summary>
    public String properties_name;

    // / <summary><br/>
    // / ����ֵ���100; For example:����ɫ
    // / </summary>
    public String properties_value;

    // / <summary><br/>
    // / ͼƬ��ַ��� 150
    // / </summary>
    public String pic;

    // / <summary><br/>
    // / ͼƬ��ַ��� 150
    // / </summary>
    public String pic_big;

    // / <summary><br/>
    // / �Ƿ����ã�Ĭ�ϲ�����; For example:��
    // / </summary>
    public Boolean enabled = null;

    // / <summary><br/>
    // / ��������λ KG; For example:1.2
    // / </summary>
    public BigDecimal weight;

    // / <summary>
    // / �г���
    // / </summary>
    public BigDecimal market_price;

    // / <summary>
    // / Ʒ������
    // / </summary>
    public String brand_name;

    // / <summary>
    // / �޸�ʱ��
    // / </summary>
    @JsonSerialize(using = Models.CustomDateSerializer.class)
    @JsonDeserialize(using = Models.CustomJsonDateDeserializer.class)
    public Date modified;

}
