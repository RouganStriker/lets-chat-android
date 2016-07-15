package com.lets_chat_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lets_chat_android.adapter.RoomListAdapter;
import com.lets_chat_android.models.Room;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class RoomsListActivityFragment extends ListFragment {
    private RoomListAdapter mAdapter;

    public RoomsListActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new RoomListAdapter(getActivity(), new ArrayList<Room>());
        setListAdapter(mAdapter);
        fetchRooms();
    }

    private void fetchRooms() {
        Log.d("LC", "Fetchign frooms");
        AsyncTask task = new AsyncTask<Object, Void, JSONArray>() {
            @Override
            protected JSONArray doInBackground(Object[] params) {
                return LetsChatAPIClient.getInstance().getRooms();
            }

            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                if (jsonArray == null) {
                    return;
                }
                Log.d("LC", "Num rooms: " + jsonArray.length());
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject room = jsonArray.getJSONObject(i);
                        mAdapter.add(new Room(room));
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
