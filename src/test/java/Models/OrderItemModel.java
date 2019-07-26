package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemModel implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4764800475207747580L;

    // / <summary><br/>
    // / �̼�SKU����Ӧ������� SKU
    // / </summary>
    public String sku_id;

    // / <summary><br/>
    // / ��վ��Ӧ���Զ���SKU���,����
    // / </summary>
    public String shop_sku_id;

    // / <summary><br/>
    // / ���ԣ��������100 ����; For example:��ɫ��32��
    // / </summary>
    public String properties_value;

    // / <summary><br/>
    // / Ӧ����������λС������λ���֣�����ע�����ܴ����˹��ļ�; For example:320.08
    // / </summary>
    public BigDecimal amount;

    // / <summary><br/>
    // / ���ۼۣ�������λС������λ���֣�; For example:8.06
    // / </summary>
    public BigDecimal base_price;

    // / <summary><br/>
    // / ��������; For example:40
    // / </summary>
    public int qty;

    // / <summary><br/>
    // / ��Ʒ����;������� 100 ����; For example:��ƤŮ�� ŷ����ʱ�м�Լͷ��ţƤ�� ���ᵥ���
    // / </summary>
    public String name;

    // / <summary>
    // / �Ӷ����ţ�������� 50 ����
    // / </summary>
    public String outer_oi_id;

}