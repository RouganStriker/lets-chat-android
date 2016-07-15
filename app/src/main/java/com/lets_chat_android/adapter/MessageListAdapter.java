package com.lets_chat_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lets_chat_android.models.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kelvin Chan on 15/02/2016.
 */
public class MessageListAdapter extends ArrayAdapter<Message> {
    public MessageListAdapter(Context context, List<Message> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_row, parent, false);
        }

        Message message = getItem(position);

        TextView tvDisplayName = (TextView) convertView.findViewById(R.id.message_display_name);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.message_username);
        TextView tvText = (TextView) convertView.findViewById(R.id.message_text);
        TextView tvDate = (TextView) convertView.findViewById(R.id.message_posted);
        ImageView ivAvatar = (ImageView) convertView.findViewById(R.id.message_avatar);

        tvDisplayName.setText(message.getOwner().getDisplayName());
        tvUsername.setText(message.getOwner().getUsername());
        tvText.setText(message.getText());
        tvDate.setText(message.getPosted().toString());
        Picasso.with(getContext()).load(message.getOwner().getAvatarURL()).into(ivAvatar);

        return convertView;
    }
}
