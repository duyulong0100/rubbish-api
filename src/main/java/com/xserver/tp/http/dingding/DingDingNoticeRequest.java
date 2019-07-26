package com.xserver.tp.http.dingding;

import java.util.List;

/**
 * 钉钉通知请求
 */
public class DingDingNoticeRequest {

    private String userIds;// 钉钉用户ID ,多个用,分隔
    private String deptIds;// 部门IDs
    private Boolean toAllUsers;// 是否企业全部用户
    private String suppilerShortName;// 工厂名
    private String registerDate;// 注册日期
    private String relateName;// 联系人
    private String tel;// 电话
    private String acceptPerson;// 申请接单人
    private Integer count;// 有几笔
    private int noticeType;// 通知类型
    private List<String> orderList;// 订单列表
    /**
     * 以下属性要去掉
     */
    @Deprecated
    private String title;// 标题
    @Deprecated
    private String content;// 信息体
    @Deprecated
    private String link;// 消息详情连接
    @Deprecated
    private String image;// 内部有图片

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuppilerShortName() {
        return suppilerShortName;
    }

    public void setSuppilerShortName(String suppilerShortName) {
        this.suppilerShortName = suppilerShortName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRelateName() {
        return relateName;
    }

    public void setRelateName(String relateName) {
        this.relateName = relateName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAcceptPerson() {
        return acceptPerson;
    }

    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public Boolean getToAllUsers() {
        return toAllUsers;
    }

    public void setToAllUsers(Boolean toAllUsers) {
        this.toAllUsers = toAllUsers;
    }

    public Integer getCount() {
        return count == null ? 1 : count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }
}
