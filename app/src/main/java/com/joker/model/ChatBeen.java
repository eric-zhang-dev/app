package com.joker.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangyue on 2016/5/24.
 */
public class ChatBeen {
    private int type;
    private String content;
    private String text;
    private int code;

    public ChatBeen() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ChatBeen(int type, String content, String text, int code) {
        this.type = type;
        this.content = content;
        this.text = text;
        this.code = code;
    }

    public static ChatBeen getObj(String s) {
        JSONObject jsonObject = null;
        ChatBeen chatBeen = null;
        try {
            jsonObject = new JSONObject(s).optJSONObject("result");
            chatBeen = new ChatBeen();
            chatBeen.setType(2);
            chatBeen.setContent(jsonObject.optString("text"));
            chatBeen.setCode(jsonObject.optInt("code"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chatBeen;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
