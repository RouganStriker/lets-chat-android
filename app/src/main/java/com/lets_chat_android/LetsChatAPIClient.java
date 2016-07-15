package com.lets_chat_android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kelvin Chan on 14/02/2016.
 */
public class LetsChatAPIClient
{
    private String server;
    private String apiToken;
    private static LetsChatAPIClient ourInstance = new LetsChatAPIClient();

    public static LetsChatAPIClient getInstance() {
        return ourInstance;
    }

    private LetsChatAPIClient() {
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String callAPI(String method, String endpoint) {
        String urlString=server.concat(endpoint); // URL to call
        InputStream in;

        // HTTP Get
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Authorization", "Bearer " + apiToken);

            Log.d("LC", urlString + " connection status " + urlConnection.getResponseMessage());

            in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            Log.d("LC", responseStrBuilder.toString());
            return responseStrBuilder.toString();
        } catch (Exception e ) {
            Log.e("LC", e.toString());
            return null;
        }
    }

    public JSONObject getCurrentUser() {
        String response = callAPI("GET", "/account");
        try {
           return new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getRooms() {
        String response = callAPI("GET", "/rooms");
        try {
            return new JSONArray(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getMessages(String roomID) {
        String response = callAPI("GET", "/rooms/" + roomID + "/messages");
        try {
            return new JSONArray(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
