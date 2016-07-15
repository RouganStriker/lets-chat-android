package com.lets_chat_android.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kelvin Chan on 15/02/2016.
 */
public class Message {
    private String text;
    private User owner;
    private Date posted;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.CANADA);

    public Message(JSONObject message) {
        try {
            this.text = message.getString("text");
            this.owner = new User(message.getJSONObject("owner"));
            this.posted = format.parse(message.getString("posted"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getPosted() {
        return posted;
    }

    public String getText() {
        return text;
    }

    public User getOwner() {
        return owner;
    }
}
