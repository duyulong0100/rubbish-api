package com.xserver;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.xserver.util.JsonMapper;

import java.util.Arrays;

/**
 * Created by lkk on 2019/5/15.
 */
public class TeskDingding {
    private static Long AgentId = 270129509L;
    private static String AppKey = "dinguisyzuxxwhs2ftvw";
    private static String AppSecret = "T-SzCICjfgJUC3flavc2MuDFrmoLFUDCqOMuI3KlOfSQG48oX7lssqhq1peVSem6";

    // 加码群:chat824487ba422f13515bb11176753f1691
    // 派单和配比群:chat5f27c48e59bcfe0c4050fdd668f95ce4
    // 交期和逾期群:chatf52cc9e208d34bd98449283b1e566858
    // 余涛userId:1211220346657762
    // 王胜:176267252829288304
    // 调度:121377443
    public static void main(String[] args) {
        String accessToken = accessToken();
        // workNotice(accessToken);
        // createCluster();
        // createDept();
        // clusterNotice(accessToken);
        queryDept();
    }

    /**
     * 工作通知
     * @param accessToken
     */
    public static void workNotice(String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient(
                "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        // request.setUseridList("176267252829288304");
        request.setUseridList("176267252829288304,1211220346657762");
        // request.setDeptIdList("121377443");
        request.setAgentId(AgentId);
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        // msg.setMsgtype("text");
        // msg.setText(new
        // OapiMessageCorpconversationAsyncsendV2Request.Text());
        // msg.getText().setContent("羽绒服100件,预计交期7月15号~");
        // request.setMsg(msg);

        // msg.setMsgtype("image");
        // msg.setImage(new
        // OapiMessageCorpconversationAsyncsendV2Request.Image());
        // msg.getImage().setMediaId("@lADOdvRYes0CbM0CbA");
        // request.setMsg(msg);
        //
        // msg.setMsgtype("file");
        // msg.setFile(new
        // OapiMessageCorpconversationAsyncsendV2Request.File());
        // msg.getFile().setMediaId("@lADOdvRYes0CbM0CbA");
        // request.setMsg(msg);
        //
        // msg.setMsgtype("link");
        // msg.setLink(new
        // OapiMessageCorpconversationAsyncsendV2Request.Link());
        // msg.getLink().setTitle("test");
        // msg.getLink().setText("test");
        // msg.getLink().setMessageUrl("http://www.baidu.com");
        // msg.getLink().setPicUrl("test");
        // request.setMsg(msg);
        //
        msg.setMsgtype("markdown");
        msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
        msg.getMarkdown().setText(
                "【衣帮衣】-待审核s工厂 您有一笔未确认交期的订单，请及时沟通，登陆www.yibyi.net处理: \n " + " \n " + " 有新的工厂信息待审核，请尽快处理 \n "
                        + " -工厂简称：红兴加工 \n " + " -注册时间：2009 \n " + " -联系人姓名：陈芾 \n " + " -联系方式：13801023423");
        msg.getMarkdown().setTitle("你有一笔新派单你有一笔新派单");
        request.setMsg(msg);
        //
        // msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
        // msg.getOa().setHead(new
        // OapiMessageCorpconversationAsyncsendV2Request.Head());
        // msg.getOa().getHead().setText("新单");
        // msg.getOa().setBody(new
        // OapiMessageCorpconversationAsyncsendV2Request.Body());
        // msg.getOa().getBody().setContent("你有两笔派单");
        // msg.setMsgtype("oa");
        // request.setMsg(msg);
        //
        // msg.setActionCard(new
        // OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        // msg.getActionCard().setTitle("衣帮衣派单");
        // msg.getActionCard().setMarkdown("你有一笔派单");
        // msg.getActionCard().setSingleTitle("你有一笔派单:羽绒服100件");
        // msg.getActionCard().setSingleUrl("https://www.baidu.com");
        // msg.setMsgtype("action_card");
        // request.setMsg(msg);

        try {
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, accessToken);
            System.out.println(response.isSuccess());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群消息通知
     * @param accessToken
     */
    public static void clusterNotice(String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/send");
        OapiChatSendRequest request = new OapiChatSendRequest();
        request.setChatid("chat9df3cfccac2f74f74b6d93a0625db798");

        OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
        msg.setMsgtype("text");
        // msg.setMsgtype("oa");
        // OapiChatSendRequest.Oa oa=new OapiChatSendRequest.Oa();
        // oa.setMessageUrl("");
        // oa.setBody(new OapiChatSendRequest.Body());
        // oa.getBody().setContent("通知");
        // oa.getBody().setForm(new ArrayList<OapiChatSendRequest.Form>());
        // OapiChatSendRequest.Form f=new OapiChatSendRequest.Form();
        // f.setKey("aaa");
        // f.setValue("bb");
        // oa.getBody().getForm().add(f);
        // oa.setHead(new OapiChatSendRequest.Head());
        // oa.getHead().setText("head");
        // oa.getHead().setBgcolor("red");
        // msg.setOa(oa);
        //
        // OapiChatSendRequest.Text text = new OapiChatSendRequest.Text();
        // text.setContent("文本消息");
        // msg.setText(text);
        msg.setText(new OapiChatSendRequest.Text());
        msg.getText().setContent(
                "【衣帮衣】-加码订单 \n " + " 您有一笔可以加码的订单，请及时沟通，登陆www.yibyi.net处理：\n" + "  \n" + " -订单号：WSG2019002302\n"
                        + " -商品款号：S0232DSDF\n" + " -商品名称：羽绒服\n" + " -设计师名称：李佳\n" + " -设计师下单数：200件\n" + " -店铺A：SSSSS\n"
                        + " -店铺N：N\n" + " -下单时间：2019-07-08");
        request.setMsg(msg);
        try {
            OapiChatSendResponse response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人普通消息
     */
    public static void noticeNotice(String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/message/send_to_conversation");

        OapiMessageSendToConversationRequest req = new OapiMessageSendToConversationRequest();
        req.setSender("01376814877479");
        req.setCid("14ac70d94e79377b88aa5fc75759fe84");
        OapiMessageSendToConversationRequest.Msg msg = new OapiMessageSendToConversationRequest.Msg();

        // // 文本消息
        // OapiMessageSendToConversationRequest.Text text = new
        // OapiMessageSendToConversationRequest.Text();
        // text.setContent("测试测试");
        // msg.setText(text);
        // msg.setMsgtype("text");
        // req.setMsg(msg);
        //
        // // 图片
        // OapiMessageSendToConversationRequest.Image image = new
        // OapiMessageSendToConversationRequest.Image();
        // image.setMediaId("@lADOdvRYes0CbM0CbA");
        // msg.setImage(image);
        // msg.setMsgtype("image");
        // req.setMsg(msg);
        //
        // // 文件
        // OapiMessageSendToConversationRequest.File file = new
        // OapiMessageSendToConversationRequest.File();
        // file.setMediaId("@lADOdvRYes0CbM0CbA");
        // msg.setFile(file);
        // msg.setMsgtype("file");
        // req.setMsg(msg);

        OapiMessageSendToConversationRequest.Markdown markdown = new OapiMessageSendToConversationRequest.Markdown();
        markdown.setText("# 这是支持markdown的文本 \n## 标题2  \n* 列表1 \n![alt 啊]2ebe0083.png)");
        markdown.setTitle("s首屏会话透出的展示内容");
        msg.setMarkdown(markdown);
        msg.setMsgtype("markdown");
        req.setMsg(msg);

        // OapiMessageSendToConversationRequest.ActionCard actionCard = new
        // OapiMessageSendToConversationRequest.ActionCard();
        // actionCard.setTitle("是透出到会话列表和通知的文案");
        // actionCard.setMarkdown("持markdown格式的正文内");
        // actionCard.setSingleTitle("查看详情");
        // actionCard.setSingleUrl("https://open.dingtalk.com");
        // msg.setActionCard(actionCard);
        // msg.setMsgtype("action_card");
        // req.setMsg(msg);
        //
        // // link消息
        // OapiMessageSendToConversationRequest.Link link = new
        // OapiMessageSendToConversationRequest.Link();
        // link.setMessageUrl("https://www.baidu.com/");
        // link.setPicUrl("@lADOdvRYes0CbM0CbA");
        // link.setText("步扬测试");
        // link.setTitle("oapi");
        // msg.setLink(link);
        // msg.setMsgtype("link");
        // req.setMsg(msg);

        try {
            OapiMessageSendToConversationResponse response = client.execute(req, accessToken);

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取token
     * @return
     */
    public static String accessToken() {
        String accessToken = null;
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(AppKey);
        request.setAppsecret(AppSecret);
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

    public static void createCluster() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/create");
        OapiChatCreateRequest request = new OapiChatCreateRequest();
        request.setName("测试群");
        request.setOwner("176267252829288304");
        request.setUseridlist(Arrays.asList(new String[] { "1211220346657762", "176267252829288304",
                "282920644624077303" }));
        request.setShowHistoryType(1L);
        try {
            OapiChatCreateResponse response = client.execute(request, accessToken());
            System.out.println(response.getChatid());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void createDept() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/create");
        OapiDepartmentCreateRequest request = new OapiDepartmentCreateRequest();
        request.setParentid("1");
        request.setCreateDeptGroup(true);
        request.setOrder("100");
        request.setName("调度部门");
        try {
            OapiDepartmentCreateResponse response = client.execute(request, accessToken());
            System.out.print(response.getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void queryDept() {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
            OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
            request.setId("121377443");
            request.setHttpMethod("GET");
            OapiDepartmentGetResponse response = client.execute(request, accessToken());
            System.out.print(JsonMapper.nonDefaultMapper().toJson(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
