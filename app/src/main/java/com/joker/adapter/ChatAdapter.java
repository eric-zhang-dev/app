package com.joker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joker.R;
import com.joker.model.ChatBeen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyue on 2016/5/24.
 */
public class ChatAdapter extends BaseAdapter {
    private List<ChatBeen> chatBeens = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public ChatAdapter(Context context) {
//        this.chatBeens = chatBeens;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return chatBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return chatBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = chatBeens.get(position).getType();
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.left_layout, null);
            holder = new ViewHolder();
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.right_layout);
            holder.leftLayout = (LinearLayout) convertView.findViewById(R.id.left_layout);
            holder.right_content = (TextView) convertView.findViewById(R.id.right_content);
            holder.right_img = (ImageView) convertView.findViewById(R.id.right_img);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            if (type == 1) {
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.linearLayout.setVisibility(View.GONE);
            } else {
                holder.linearLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (type == 1) {
            holder.img.setBackgroundResource(R.mipmap.ic_launcher);
            holder.content.setText(chatBeens.get(position).getContent());
        } else {
            holder.right_img.setBackgroundResource(R.mipmap.ic_launcher);
            holder.right_content.setText(chatBeens.get(position).getContent());
        }

        return convertView;
    }

    private class ViewHolder {
        TextView content, right_content;
        ImageView img, right_img;
        LinearLayout linearLayout;
        LinearLayout leftLayout;
    }

    public void notifys(ChatBeen list) {
        chatBeens.add(list);
        notifyDataSetChanged();
    }
}
