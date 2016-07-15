package com.lets_chat_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lets_chat_android.adapter.MessageListAdapter;
import com.lets_chat_android.models.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MessagesFragment extends ListFragment {
    private MessageListAdapter mAdapter;
    private String mRoomID;

    public MessagesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRoomID = savedInstanceState.getString("ROOM_ID");

        mAdapter = new MessageListAdapter(getActivity(), new ArrayList<Message>());
        setListAdapter(mAdapter);
        fetchMessages();
    }

    private void fetchMessages() {
        Log.d("LC", "Fetchign messages");
        AsyncTask task = new AsyncTask<Object, Void, JSONArray>() {
            @Override
            protected JSONArray doInBackground(Object[] params) {
                return LetsChatAPIClient.getInstance().getMessages(mRoomID);
            }

            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                if (jsonArray == null) {
                    return;
                }

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject message = jsonArray.getJSONObject(i);
                        mAdapter.add(new Message(message));
                    }
                } catch (Exception error) {
                    Log.e("LC", error.toString());
                }
                mAdapter.notifyDataSetChanged();
            }
        };
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms_list, container, false);
    }
}
