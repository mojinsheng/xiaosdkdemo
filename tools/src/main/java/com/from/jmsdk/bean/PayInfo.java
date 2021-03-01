package com.from.jmsdk.bean;


//TODO variables should be rewrite if backend api defined
//TODO This class is export to cp
public class PayInfo {

    private String cpOrderId="";
    private String u_id="";
    private String game_id="";
    private String subject="";
    private String subjectCount="";

    private String server_name="";
    private String role_name="";
    private String role_id="";
    private int role_level;
    private String server_id="";
    private double money;
    private String extInfo="";
    private String buyAmount="";
    private String channel_id="";
    private String sign="";
    private String productId="";
    private String productName="";
    private String productDesc="";
    private String vip="";

    public String getCpOrderId() {
        return cpOrderId;
    }

    public void setCpOrderId(String cpOrderId) {
        this.cpOrderId = cpOrderId;
    }

    public String getGame_id() {
        return game_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(String subjectCount) {
        this.subjectCount = subjectCount;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String server_id) {
        this.server_id = server_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public int getRole_level() {
        return role_level;
    }

    public void setRole_level(int role_level) {
        this.role_level = role_level;
    }
}
