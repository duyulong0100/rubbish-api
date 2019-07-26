package com.xserver.tp.http.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import com.xserver.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 */
@Repository
public class DingDingHttp {
    public static int DAI_JIA_MA_ORDER = 1;// 待加码
    public static int DAI_PAI_DAN = 2;// 待派单
    public static int DAI_PEI_BI = 3;// 待配比
    public static int DAI_JIAO_QI_QUE_REN = 4;// 待交期确认
    public static int JI_JIANG_YU_QI_ORDER = 5;// 即将逾期订单
    public static int GONG_CHANG_XIN_XI_SHEN_HE = 6;// 工厂信息审核
    public static int JIE_DAN_QUAN_XIAN_SHEN_HE = 7;// 接单权限审核
    public static int YU_QI_ORDER = 8;// 已逾期
    public static int DAI_JIE_ORDER = 9;// 待接单
    protected final Log LOG = LogFactory.getLog(this.getClass());
    @Value("${dingding.agentid}")
    private Long agentId;
    @Value("${dingding.appkey}")
    private String appKey;
    @Value("${dingding.appsecret}")
    private String appSecret;
    @Value("${dingding.noticeurl}")
    private String noticeUrl;
    @Value("${dingding.clusterurl}")
    private String clusterUrl;
    @Value("${dingding.accesstokenurl}")
    private String accessTokenUrl;
    private String msgType = "markdown";
    private String TITLE_KEY = "TITLE";
    private String CONTENT_KEY = "CONTENT";
    private String PRE = "###### ";
    private String ENTER = " \n ";

    /**
     * 工作通知
     * @param req
     * @return
     */
    public DingDingResponse workNotice(DingDingNoticeRequest req) {
        LOG.info(req + ",发送通知开始");
        DingDingResponse dingDingResponse = new DingDingResponse();
        DingTalkClient client = new DefaultDingTalkClient(noticeUrl);

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        if (req.getUserIds() != null) {
            request.setUseridList(req.getUserIds());
        }
        if (req.getDeptIds() != null) {
            request.setDeptIdList(req.getDeptIds());
        }
        request.setAgentId(agentId);
        if (req.getToAllUsers() != null) {
            request.setToAllUser(req.getToAllUsers());
        } else {
            request.setToAllUser(false);
        }
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype(msgType);
        msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
        Map<String, String> msgContent = null;
        switch (req.getNoticeType()) {
        case 5: {
            msgContent = getNO5(null, req);
            break;
        }
        case 6: {
            msgContent = getNO6(req);
            break;
        }
        case 7: {
            msgContent = getNO7(req);
            break;
        }
        case 8: {
            msgContent = getNO8(null, req);
            break;
        }
        case 9: {
            msgContent = getNO9(req);
            break;
        }
        }
        if (msgContent == null || msgContent.get(CONTENT_KEY) == null) {
            dingDingResponse.setSuccess(false);
            return dingDingResponse;
        }
        msg.getMarkdown().setText(msgContent.get(CONTENT_KEY));
        msg.getMarkdown().setTitle(msgContent.get(TITLE_KEY));
        request.setMsg(msg);
        try {
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, accessToken());
            if (response != null && response.isSuccess()) {
                LOG.info(req + ",发送通知成功");
                dingDingResponse.setSuccess(true);
            } else {
                dingDingResponse.setSuccess(false);
            }
        } catch (Exception e) {
            LOG.warn("noice error.", e);
        }
        return dingDingResponse;
    }

    /**
     * 群消息
     * @param req
     * @return
     */
    public DingDingResponse workClusterMsg(DingDingClusterMsgRequest req) {
        LOG.info(req + ",发送群消息开始");
        DingDingResponse dingDingResponse = new DingDingResponse();
        DingTalkClient client = new DefaultDingTalkClient(clusterUrl);
        OapiChatSendRequest request = new OapiChatSendRequest();
        request.setChatid(req.getChatId());
        OapiChatSendRequest.Markdown markdown = new OapiChatSendRequest.Markdown();
        Map<String, String> msgContent = null;
        switch (req.getNoticeType()) {
        case 1: {
            msgContent = getNO1(req);
            break;
        }
        case 2: {
            msgContent = getNO2(req);
            break;
        }
        case 3: {
            msgContent = getNO3(req);
            break;
        }
        case 4: {
            msgContent = getNO4(req);
            break;
        }
        case 5: {
            msgContent = getNO5(req, null);
            break;
        }
        case 8: {
            msgContent = getNO8(req, null);
            break;
        }

        }
        if (msgContent == null || msgContent.get(CONTENT_KEY) == null) {
            dingDingResponse.setSuccess(false);
            return dingDingResponse;
        }

        markdown.setText(msgContent.get(CONTENT_KEY));
        markdown.setTitle(msgContent.get(TITLE_KEY));
        request.setMarkdown(markdown);
        request.setMsgtype(msgType);
        try {
            OapiChatSendResponse response = client.execute(request, accessToken());
            if (response != null && response.isSuccess()) {
                LOG.info(req + ",发送群成功");
                dingDingResponse.setSuccess(true);
            } else {
                dingDingResponse.setSuccess(false);
            }
        } catch (Exception e) {
            LOG.warn("noice error.", e);
        }
        return dingDingResponse;
    }

    /**
     * 待加码消息
     * @param request
     * @return
     */
    private Map<String, String> getNO1(DingDingClusterMsgRequest request) {
        Map<String, String> result = new HashMap<>();
        StringBuilder content = new StringBuilder();
        content.append("【衣帮衣】-加码订单 " + ENTER);
        content.append(PRE + "您有" + request.getCount() + "笔可以加码的订单，请及时沟通，登陆[www.yibyi.net](http://www.yibyi.net)处理:"
                + ENTER);
        content.append(PRE + convertStyleBold("-订单日期：") + request.getOrderDate() + ENTER);
        if (request.getOrderNo() != null) {
            content.append(PRE + convertStyleBold("-订 单 号：") + request.getOrderNo() + ENTER);
        }
        content.append(PRE + convertStyleBold("-款    号：") + request.getGoodsNo() + ENTER);
        content.append(PRE + convertStyleBold("-名    称：") + request.getGoodsName() + ENTER);
        content.append(PRE + convertStyleBold("-设 计 师：") + request.getDesigner() + ENTER);
        content.append(PRE + convertStyleBold("-订单数量：") + request.getOrderCount() + ENTER);
        if (request.getShopOrderCount() == null || request.getShopOrderCount().isEmpty()) {
            return result;
        }
        for (String shop : request.getShopOrderCount().keySet()) {
            content.append(PRE + convertStyleBold("-" + shop + "：") + request.getShopOrderCount().get(shop) + ENTER);
        }
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "您有" + request.getCount() + "笔可以加码的订单");
        return result;
    }

    private String convertStyleBold(String str) {
        return "" + str + "";
    }

    /**
     * 待派单消息
     * @param request
     * @return
     */
    private Map<String, String> getNO2(DingDingClusterMsgRequest request) {
        Map<String, String> result = new HashMap<>();
        StringBuilder shopStr = new StringBuilder();
        for (String shop : request.getShopList()) {
            shopStr.append(shop).append("、");
        }
        if (shopStr.length() > 0) {
            shopStr.deleteCharAt(shopStr.length() - 1);
        }
        StringBuilder content = new StringBuilder();
        content.append("【衣帮衣】-待派单订单" + ENTER);
        content.append(PRE + "您有" + request.getCount() + "笔待派单的订单，请及时沟通，登陆[www.yibyi.net](http://www.yibyi.net)处理："
                + ENTER);
        content.append(PRE + "-订单日期：" + request.getOrderDate() + ENTER);
        content.append(PRE + "-订 单 号：" + request.getOrderNo() + ENTER);
        content.append(PRE + "-款    号：" + request.getGoodsNo() + ENTER);
        content.append(PRE + "-名    称：" + request.getGoodsName() + ENTER);
        content.append(PRE + "-涉及店铺：" + shopStr.toString() + ENTER);
        content.append(PRE + "-订单数量：" + request.getOrderCount() + ENTER);
        content.append(PRE + "-交货日期：" + request.getReportDate() + ENTER);
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "您有" + request.getCount() + "笔待派单的订单");
        return result;
    }

    /**
     * 待配比消息
     * @param request
     * @return
     */
    private Map<String, String> getNO3(DingDingClusterMsgRequest request) {
        Map<String, String> result = new HashMap<>();
        StringBuilder shopStr = new StringBuilder();
        for (String shop : request.getUnPeiBiShop()) {
            shopStr.append(shop).append("、");
        }
        if (shopStr.length() > 0) {
            shopStr.deleteCharAt(shopStr.length() - 1);
        }
        StringBuilder content = new StringBuilder();
        content.append("【衣帮衣】-未配比订单" + ENTER);
        content.append(PRE + "您有" + request.getCount() + "笔未配比订单，请及时沟通，登陆[www.yibyi.net](http://www.yibyi.net)处理："
                + ENTER);
        content.append(PRE + "-未配比店铺：" + shopStr.toString() + ENTER);
        content.append(PRE + "-订单日期：" + request.getOrderDate() + ENTER);
        content.append(PRE + "-订 单 号：" + request.getOrderNo() + ENTER);
        content.append(PRE + "-订单数量：" + request.getOrderCount() + ENTER);
        content.append(PRE + "-款    号：" + request.getGoodsNo() + ENTER);
        content.append(PRE + "-名    称：" + request.getGoodsName() + ENTER);
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "您有" + request.getCount() + "笔未配比订单");
        return result;
    }

    /**
     * 待交期确认消息
     * @param request
     * @return
     */
    private Map<String, String> getNO4(DingDingClusterMsgRequest request) {
        Map<String, String> result = new HashMap<>();
        StringBuilder shopStr = new StringBuilder();
        for (String shop : request.getUnPeiBiShop()) {
            shopStr.append(shop).append("、");
        }
        if (shopStr.length() > 0) {
            shopStr.deleteCharAt(shopStr.length() - 1);
        }
        StringBuilder content = new StringBuilder();
        content.append("【衣帮衣】-未确认交期" + ENTER);
        content.append(PRE + "您有" + request.getCount() + "笔未确认交期的订单，请及时沟通，登陆[www.yibyi.net](http://www.yibyi.net)处理："
                + ENTER);
        content.append(PRE + "-未确认店铺：" + shopStr.toString() + ENTER);
        content.append(PRE + "-报工交期：" + request.getReportDate() + ENTER);
        content.append(PRE + "-订单日期：" + request.getOrderDate() + ENTER);
        content.append(PRE + "-订 单 号：" + request.getOrderNo() + ENTER);
        content.append(PRE + "-款    号：" + request.getGoodsNo() + ENTER);
        content.append(PRE + "-名    称：" + request.getGoodsName() + ENTER);
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "您有" + request.getCount() + "笔未确认交期的订单");
        return result;
    }

    /**
     * 即将逾期消息/通知
     * @return
     */
    private Map<String, String> getNO5(DingDingClusterMsgRequest clusterRequest, DingDingNoticeRequest noticeRequest) {
        Map<String, String> result = new HashMap<>();
        List<String> orderList = new ArrayList<>();
        if (clusterRequest != null) {
            orderList = clusterRequest.getOrderList();
        }
        if (noticeRequest != null) {
            orderList = noticeRequest.getOrderList();
        }
        StringBuilder sb = new StringBuilder();

        if (orderList != null && orderList.size() > 0) {
            for (String o : orderList) {
                sb.append(PRE).append(o).append(ENTER);
            }
        } else {
            return result;
        }
        StringBuilder content = new StringBuilder();
        content.append(PRE + "【衣帮衣】-逾期统计" + ENTER);
        content.append(ENTER);
        content.append(PRE + "截止今天，近10日已逾期的订单有：" + ENTER);
        content.append(sb.toString());
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "【衣帮衣】-逾期统计");
        return result;
    }

    /**
     * 工厂信息审核消息
     * @param noticeRequest
     * @return
     */
    private Map<String, String> getNO6(DingDingNoticeRequest noticeRequest) {
        Map<String, String> result = new HashMap<>();
        StringBuilder content = new StringBuilder();
        content.append(PRE + "【衣帮衣】-待审核工厂" + ENTER);
        content.append(PRE + " 您有" + noticeRequest.getCount()
                + "个待审核工厂，请尽快处理，登陆[www.yibyi.net](http://www.yibyi.net)处理" + ENTER);
        content.append(PRE + "-工厂简称：" + noticeRequest.getSuppilerShortName() + ENTER);
        content.append(PRE + " -注册时间：" + noticeRequest.getRegisterDate() + ENTER);
        content.append(PRE + " -联系人姓名：" + noticeRequest.getRelateName() + ENTER);
        content.append(PRE + " -联系方式：" + noticeRequest.getTel());
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "【衣帮衣】-待审核工厂");
        return result;
    }

    /**
     * 接单权限审核消息
     * @param noticeRequest
     * @return
     */
    private Map<String, String> getNO7(DingDingNoticeRequest noticeRequest) {
        Map<String, String> result = new HashMap<>();
        StringBuilder content = new StringBuilder();
        content.append(" 【衣帮衣】-待审核工厂" + ENTER);
        content.append(PRE + " 您有新的接单权限申请待审核，请尽快处理" + ENTER);
        content.append(PRE + " -申请接单人：" + noticeRequest.getAcceptPerson() + ENTER);
        content.append(PRE + " -联系方式：" + noticeRequest.getTel() + ENTER);
        content.append(PRE + " -所在工厂：" + noticeRequest.getSuppilerShortName());
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "【衣帮衣】-待审核工厂");

        return result;
    }

    /**
     * 已逾期消息/通知
     * @return
     */
    private Map<String, String> getNO8(DingDingClusterMsgRequest clusterRequest, DingDingNoticeRequest noticeRequest) {
        Map<String, String> result = new HashMap<>();
        List<String> orderList = new ArrayList<>();
        if (clusterRequest != null) {
            orderList = clusterRequest.getOrderList();
        }
        if (noticeRequest != null) {
            orderList = noticeRequest.getOrderList();
        }
        StringBuilder sb = new StringBuilder();
        if (orderList != null && orderList.size() > 0) {
            for (String o : orderList) {
                sb.append(PRE).append(o).append(ENTER);
            }
        } else {
            return result;
        }
        StringBuilder content = new StringBuilder();
        content.append("【衣帮衣】-交期预警" + ENTER);
        content.append(PRE + "按照订单约定，以下订单3天内到交货日期，请关注，详情见：" + ENTER);
        content.append(sb.toString());
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "【衣帮衣】-交期预警");
        return result;
    }

    /**
     * 已逾期消息/通知
     * @return
     */
    private Map<String, String> getNO9(DingDingNoticeRequest noticeRequest) {
        Map<String, String> result = new HashMap<>();
        StringBuilder content = new StringBuilder();
        content.append("【衣帮衣】待接单" + ENTER);
        content.append(PRE + DateUtils.formatDate(DateUtils.DATE_TIME_MIN_FORMAT, new Date())
                + " 您有1笔待接单的订单，请及时登录衣帮衣处理：" + ENTER);
        result.put(CONTENT_KEY, content.toString());
        result.put(TITLE_KEY, "【衣帮衣】待接单");
        return result;
    }

    /**
     * 获取token
     * @return
     */
    public String accessToken() {
        String accessToken = null;
        DefaultDingTalkClient client = new DefaultDingTalkClient(accessTokenUrl);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod("GET");
        try {
            OapiGettokenResponse response = client.execute(request);
            if (response != null) {
                accessToken = response.getAccessToken();
            }
            System.out.println(response);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println("accessToken===" + accessToken);
        return accessToken;
    }
}
