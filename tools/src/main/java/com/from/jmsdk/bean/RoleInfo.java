package com.from.jmsdk.bean;


//TODO variables should be rewrite if backend api defined
//TODO This class is export to cp
public class RoleInfo {

//    extraData.setMoneyNum(0 + ""); // 玩家身上元宝数量，拿不到或者未获取时传0
//extraData.setRoleCreateTime(System.currentTimeMillis() / 1000); // 角色创建时间，未获取或未创建角色时传0
//extraData.setGuildId("GH10001");// 公会id，无公会或未获取时传null
//extraData.setGuildName("公会名称");// 公会名称，无公会或未获取时传null
//extraData.setGuildLevel(100 + "");// 公会等级，无公会或未获取时传0
//extraData.setGuildLeader("公会会长名");// 公会会长名称，无公会或未获取时传null
//extraData.setPower(12345);// 角色战斗力, 不能为空，必须是数字，不能为null,若无,传0
//extraData.setProfessionid(123);//职业ID，不能为空，必须为数字，若无，传入 0
//extraData.setProfession("职业名称");//职业名称，不能为空，不能为 null，若无，传入 “无”
//extraData.setGender("性别");//角色性别，不能为空，不能为 null，可传入参数“ 男、女、无”
//extraData.setProfessionroleid(123);//职业称号ID，不能为空，不能为 null，若无，传入 0
//extraData.setProfessionrolename("职业称号");//职业称号，不能为空，不能为 null，若无，传入“ 无”
//extraData.setVip(9);//玩家VIP等级，不能为空，必须为数字,若无，传入 0
//GamePlatform.getInstance().gameSubmitExtendData (GameMainActivity.this,extraData);

    private String u_id;
    private String game_id;
    private String server_name;
    private String role_name;
    private String role_id;
    private String server_id;
    private int level;
    private String sign;
    private String moneyNum;
    private String roleCreateTime;
    private String guildId;
    private String guildName;
    private String guildLevel;
    private String guildLeader;
    private String power;
    private String professionid;
    private String profession;
    private String gender;
    private String professionroleid;
    private String professionrolename;
    private String vip;






    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(String moneyNum) {
        this.moneyNum = moneyNum;
    }

    public String getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(String roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getGuildLevel() {
        return guildLevel;
    }

    public void setGuildLevel(String guildLevel) {
        this.guildLevel = guildLevel;
    }

    public String getGuildLeader() {
        return guildLeader;
    }

    public void setGuildLeader(String guildLeader) {
        this.guildLeader = guildLeader;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getProfessionid() {
        return professionid;
    }

    public void setProfessionid(String professionid) {
        this.professionid = professionid;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfessionroleid() {
        return professionroleid;
    }

    public void setProfessionroleid(String professionroleid) {
        this.professionroleid = professionroleid;
    }

    public String getProfessionrolename() {
        return professionrolename;
    }

    public void setProfessionrolename(String professionrolename) {
        this.professionrolename = professionrolename;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
