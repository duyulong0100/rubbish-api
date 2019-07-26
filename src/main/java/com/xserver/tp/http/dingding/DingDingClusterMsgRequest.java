package com.xserver.tp.http.dingding;

import java.util.List;
import java.util.Map;

/**
 * 钉钉通知请求
 */
public class DingDingClusterMsgRequest {
    @Deprecated
    private String title;// 标题
    @Deprecated
    private String content;// 信息体
    private String chatId;// 群会话ID
    private int noticeType;// 通知类型
    private String orderDate;// 下单日期
    private String orderNo;// 订单号
    private String goodsNo;// 商品款号
    private String goodsName;// 商品名
    private String designer;// 设计师名
    private Integer orderCount;// 订单件数
    private Map<String, Integer> shopOrderCount;// 店铺下单数量:例如:A:20,B:20
    private List<String> shopList;// 涉及店铺
    private String reportDate;// 交货日期
    private List<String> unPeiBiShop;// 未配比店铺/未确认交期店铺
    private String toDoReportDate;// 待交期确认
    private Integer nearDays;// 近几天逾期
    private List<String> orderList;// 即将逾期订单列表
    private Integer count;// 有几笔

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Map<String, Integer> getShopOrderCount() {
        return shopOrderCount;
    }

    public void setShopOrderCount(Map<String, Integer> shopOrderCount) {
        this.shopOrderCount = shopOrderCount;
    }

    public List<String> getShopList() {
        return shopList;
    }

    public void setShopList(List<String> shopList) {
        this.shopList = shopList;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public List<String> getUnPeiBiShop() {
        return unPeiBiShop;
    }

    public void setUnPeiBiShop(List<String> unPeiBiShop) {
        this.unPeiBiShop = unPeiBiShop;
    }

    public String getToDoReportDate() {
        return toDoReportDate;
    }

    public void setToDoReportDate(String toDoReportDate) {
        this.toDoReportDate = toDoReportDate;
    }

    public Integer getNearDays() {
        return nearDays;
    }

    public void setNearDays(Integer nearDays) {
        this.nearDays = nearDays;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }

    public Integer getCount() {
        return count == null ? 1 : count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
