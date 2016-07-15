package com.lets_chat_android.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kelvin Chan on 13/02/2016.
 */
public class Room {
    private String id;
    private String slug;
    private String name;
    private String description;

    public Room(String id, String slug, String name, String description) {
        this.id = id;
        this.slug = "#".concat(slug);
        this.name = name;
        this.description = description;
    }

    public Room(JSONObject room) {
        try {
            this.id = room.getString("id");
            this.slug = "#" + room.getString("slug");
            this.name = room.getString("name");
            this.description = room.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}
