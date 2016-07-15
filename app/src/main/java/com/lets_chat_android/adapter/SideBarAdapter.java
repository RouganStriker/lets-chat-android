package com.lets_chat_android.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lets_chat_android.R;
import com.lets_chat_android.models.Room;

import java.util.ArrayList;

/**
 * Created by Kelvin Chan on 13/02/2016.
 */
public class SideBarAdapter extends ArrayAdapter<Pair<Room, Integer>> {
    public SideBarAdapter(Context context, ArrayList<Pair<Room, Integer>> selectedRooms) {
        super(context, 0, selectedRooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_bar_row, parent, false);
        }

        Pair<Room, Integer> data = getItem(position);
        TextView tvRoom = (TextView) convertView.findViewById(R.id.nav_room_name);
        tvRoom.setText(data.first.getName());

        return convertView;
    }
}
