package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBaseModel implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 9010417105228211181L;

    // / <summary><br/>
    // / ���̱��; For example:168
    // / </summary>
    public int shop_id;

    // / <summary><br/>
    // / �����ţ�������� 50;Ψһ; For example:2a32312321688
    // / </summary>
    public String so_id;

    // / <summary><br/>
    // / �µ�ʱ��; For example:41954.4661
    // / </summary>
    @JsonSerialize(using = Models.CustomDateSerializer.class)
    @JsonDeserialize(using = Models.CustomJsonDateDeserializer.class)
    public Date order_date;

    // / <summary><br/>
    // /
    // ����״̬;ȡ��״̬��ѡ(Cancelled,TRADE_CLOSED),����״̬��ѡ��Sent��TRADE_SHIPPED��TRADE_RECEIVED��TRADE_FINISHED��������״̬��ѡ��Question��
    // / </summary>
    public String shop_status;

    // / <summary>
    // / ��������
    // / </summary>
    public String question_type;

    // / <summary>
    // / ��������
    // / </summary>
    public String question_desc;

    // / <summary><br/>
    // / ����ǳ�
    // / </summary>
    public String shop_buyer_id;

    // / <summary><br/>
    // / �ջ�ʡ��; For example:�㽭ʡ
    // / </summary>
    public String receiver_state;

    // / <summary><br/>
    // / �ջ���; For example:������
    // / </summary>
    public String receiver_city;

    // / <summary><br/>
    // / ��; For example:��ɽ��
    // / </summary>
    public String receiver_district;

    // / <summary><br/>
    // / �ջ���ַ; For example:***·***����**��
    // / </summary>
    public String receiver_address;

    // / <summary><br/>
    // / �ռ���; For example:����
    // / </summary>
    public String receiver_name;

    // / <summary><br/>
    // / �̶��绰
    // / </summary>
    public String receiver_phone;

    // / <summary><br/>
    // / �ֻ�; For example:13758082608
    // / </summary>
    public String receiver_mobile;

    // / <summary><br/>
    // / ָ����������
    // / </summary>
    @JsonSerialize(using = Models.CustomDateSerializer.class)
    @JsonDeserialize(using = Models.CustomJsonDateDeserializer.class)
    public Date send_date;

    // / <summary><br/>
    // / Ӧ����������λС������λ���֣�
    // / </summary>
    public BigDecimal pay_amount;

    // / <summary><br/>
    // / �˷ѣ�������λС������λ���֣�; For example:10
    // / </summary>
    public BigDecimal freight;

    // / <summary><br/>
    // / ������ԣ��
    // / </summary>
    public String buyer_message;

    // / <summary><br/>
    // / ��ע��� 150
    // / </summary>
    public String remark;

    // / <summary><br/>
    // / ��Ʊ̧ͷ��� 50
    // / </summary>
    public String invoice_title;

    // / <summary><br/>
    // / �Ƿ��������
    // / </summary>
    public Boolean is_cod;

    // / <summary>
    // / �����ܽ�Ӧ�����=��ϸ���+�˷�
    // / </summary>
    public BigDecimal amount;

    // / <summary>
    // / �޸�ʱ��
    // / </summary>
    public Date modified;

    // / <summary>
    // / �ʱ�
    // / </summary>
    public String receiver_zip;

    // / <summary>
    // / ������Դ
    // / </summary>
    public String order_from;

    // / <summary>
    // / ������ϸ����Ʒ��Ϣ
    // / </summary>
    public List<OrderItemModel> items = new ArrayList<OrderItemModel>();

    // /֧����Ϣ
    public PayModel pay;
}
