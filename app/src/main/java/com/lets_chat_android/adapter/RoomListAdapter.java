package com.lets_chat_android.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.lets_chat_android.models.Room;

import java.util.List;


public class RoomListAdapter extends ArrayAdapter<Room> {
    public RoomListAdapter(Context context, List<Room> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.room_list_row, parent, false);
        }

        Room room = getItem(position);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.room_list_row_title);
        TextView tvSlug = (TextView) convertView.findViewById(R.id.room_list_row_slug);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.room_list_row_description);
        Switch btnFavourite = (Switch) convertView.findViewById(R.id.room_list_row_toggle);

        tvTitle.setText(room.getName());
        tvSlug.setText(room.getSlug());
        tvDescription.setText(room.getDescription());

        return convertView;
    }
}
