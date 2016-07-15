package com.lets_chat_android.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kelvin Chan on 13/02/2016.
 */
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String displayName;
    private String avatarURL;
    private final String gravatar_url = "http://www.gravatar.com/avatar/";

    public User(String id, String firstName, String lastName, String username, String displayName, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = "@".concat(username);
        this.displayName = displayName;
        this.avatarURL = gravatar_url.concat(avatar).concat(".jpg");
    }

    public User(JSONObject account_json) throws JSONException {
        this(
            account_json.getString("id"),
            account_json.getString("firstName"),
            account_json.getString("lastName"),
            account_json.getString("username"),
            account_json.getString("displayName"),
            account_json.getString("avatar")
        );
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }
}
