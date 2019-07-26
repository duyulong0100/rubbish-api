package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayModel implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8894170247498551205L;

    // / <summary><br/>
    // / �ⲿ֧������; For example:0141112145
    // / </summary>
    public String outer_pay_id;

    // / <summary><br/>
    // / ֧��ʱ��; For example:2014/11/11 16:12:12
    // / </summary>
    @JsonSerialize(using = Models.CustomDateSerializer.class)
    @JsonDeserialize(using = Models.CustomJsonDateDeserializer.class)
    public Date pay_date;

    // / <summary><br/>
    // / ֧����������λС������λ(��); For example:345.98
    // / </summary>
    public BigDecimal amount;

    // / <summary><br/>
    // / �����ţ�������� 50;Ψһ; For example:3231232169
    // / </summary>
    public String so_id;

    // / <summary><br/>
    // / ֧����ʽ; For example:֧����
    // / </summary>
    public String payment;

    // / <summary><br/>
    // / ���֧���˺ţ�������� 50; For example:qjh6877@163.com
    // / </summary>
    public String buyer_account;

    // / <summary><br/>
    // / ����֧���˺� ������� 50; For example:455834@qq.com
    // / </summary>
    public String seller_account;

}
