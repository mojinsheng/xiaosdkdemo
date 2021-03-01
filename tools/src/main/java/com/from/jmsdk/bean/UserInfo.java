package com.from.jmsdk.bean;

/**
 * <p>
 *     UserInfo storage all the data to CP after Login action.
 * </p>
 * <p>
 * </p>
 */
@SuppressWarnings("WeakerAccess")
public class UserInfo {

    private int code;
    private String userId;
    private String message;
    private String sign;
    private String channelExt;
    private String channel_server_id;
    private String game_id;
    private String channel_id;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getChannelExt() {
        return channelExt;
    }

    public void setChannelExt(String channelExt) {
        this.channelExt = channelExt;
    }

    public String getChannel_server_id() {
        return channel_server_id;
    }

    public void setChannel_server_id(String channel_server_id) {
        this.channel_server_id = channel_server_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
