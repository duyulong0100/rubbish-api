import Api.Utility;
import Models.OrderBaseModel;
import Models.OrderItemModel;
import Models.PayModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        UpOrders();

    }

    // �ϴ�����
    private static void UpOrders() throws IOException {
        String sPartnerId = "ywv5jGT8ge6Pvlq3FZSPol345asd";
        String sPartnerKey = "ywv5jGT8ge6Pvlq3FZSPol2323";
        String sToken = "181ee8952a88f5a57db52587472c3798";
        String sHostUrl = "http://b.sursung.com/api/open/query.aspx";
        String sMethod = "orders.upload";

        OrderBaseModel orderBaseModel = new OrderBaseModel();
        orderBaseModel.shop_id = 14588;
        orderBaseModel.so_id = "201612141014";
        orderBaseModel.order_date = new Date();
        orderBaseModel.shop_status = "WAIT_BUYER_PAY";
        orderBaseModel.shop_buyer_id = "SKy";
        orderBaseModel.receiver_state = "�㽭ʡ";
        orderBaseModel.receiver_city = "������";
        orderBaseModel.receiver_district = "�Ϻ���";
        orderBaseModel.receiver_address = "����·134�� ������";
        orderBaseModel.receiver_name = "���";
        orderBaseModel.pay_amount = new BigDecimal(100);
        orderBaseModel.freight = new BigDecimal(10);
        orderBaseModel.receiver_mobile = "13868093605";

        OrderItemModel orderItemModel = new OrderItemModel();
        orderItemModel.sku_id = "1000000000202";
        orderItemModel.shop_sku_id = "12124";
        orderItemModel.amount = new BigDecimal(100);
        orderItemModel.base_price = new BigDecimal(200);
        orderItemModel.qty = 2;
        orderItemModel.name = "��ƤŮ�� ŷ����ʱ�м�Լͷ��ţƤ�� ���ᵥ��� ";
        orderItemModel.outer_oi_id = "1213324";
        orderBaseModel.items.add(orderItemModel);
        List<OrderBaseModel> orderList = new ArrayList<>();
        orderList.add(orderBaseModel);

        OrderBaseModel orderBasePayModel = new OrderBaseModel();
        orderBasePayModel.shop_id = 14588;
        orderBasePayModel.so_id = "201612141028";
        orderBasePayModel.order_date = new Date();
        orderBasePayModel.shop_status = "WAIT_SELLER_SEND_GOODS";
        orderBasePayModel.shop_buyer_id = "cuifeng";
        orderBasePayModel.receiver_state = "�Ϻ�";
        orderBasePayModel.receiver_city = "�Ϻ�";
        orderBasePayModel.receiver_district = "������";
        orderBasePayModel.receiver_address = "�Ͼ�·134�Ų�����";
        orderBasePayModel.receiver_name = "���";
        orderBasePayModel.pay_amount = new BigDecimal(1000);
        orderBasePayModel.freight = new BigDecimal(10);
        orderBasePayModel.receiver_mobile = "13768093205";

        OrderItemModel orderItemPayModel = new OrderItemModel();
        orderItemPayModel.sku_id = "1000000000203";
        orderItemPayModel.shop_sku_id = "12124";
        orderItemPayModel.amount = new BigDecimal(1000);
        orderItemPayModel.base_price = new BigDecimal(1200);
        orderItemPayModel.qty = 1;
        orderItemPayModel.name = "��ƤŮ��";
        orderItemPayModel.outer_oi_id = "12133324";
        orderBasePayModel.items.add(orderItemPayModel);

        orderBasePayModel.pay = new PayModel();
        orderBasePayModel.pay.outer_pay_id = "201612141010";
        orderBasePayModel.pay.pay_date = new Date();
        orderBasePayModel.pay.payment = "֧����";
        orderBasePayModel.pay.seller_account = "13758098765@163.com";
        orderBasePayModel.pay.buyer_account = "qjh6877@163.com";
        orderBasePayModel.pay.amount = new BigDecimal(1010);

        orderList.add(orderBasePayModel);

        Api.OpenSearch api = new Api.OpenSearch(sPartnerId, sPartnerKey, sToken, sMethod, sHostUrl);

        Models.JobModel jobModel = new Models.JobModel();

        String sOrderString = Utility.Serialize(orderList);
        System.out.println(sOrderString);

        jobModel.setData(sOrderString);
        String sData = api.QueryData(jobModel);
        System.out.println(sData);

    }

    // ��ѯ��Ʒ
    private static void SearchSku() throws IOException {
        String sPartnerId = "ywv5jGT8ge6Pvlq3FZSPol345asd";
        String sPartnerKey = "ywv5jGT8ge6Pvlq3FZSPol2323";
        String sToken = "181ee8952a88f5a57db52587472c3798";
        String sHostUrl = "http://b.sursung.com/api/open/query.aspx";
        String sMethod = "sku.query";

        Api.OpenSearch api = new Api.OpenSearch(sPartnerId, sPartnerKey, sToken, sMethod, sHostUrl);

        Models.JobModel jobModel = new Models.JobModel();
        Search.SearchSkuModel search = new Search.SearchSkuModel();
        search.sku_ids = "1000000000202,1000000000203";
        ObjectMapper mapper = new ObjectMapper();
        jobModel.setData(mapper.writeValueAsString(search));
        String sData = api.QueryData(jobModel);
        System.out.println(jobModel.getData());
        System.out.println(sData);
        Response.SkuResponse result = mapper.readValue(sData, Response.SkuResponse.class);
        System.out.println(mapper.writeValueAsString(result));

    }

}
