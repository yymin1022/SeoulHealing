package com.defcon.seoulhealing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HealingListAdapter extends BaseAdapter{
    private ArrayList<HealingListItem> listViewItemList = new ArrayList<HealingListItem>();

    public HealingListAdapter(){
    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_healing_item, parent, false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.list_item_img);
        TextView titleTextView = convertView.findViewById(R.id.list_item_title);
        TextView descTextView = convertView.findViewById(R.id.list_item_address);

        HealingListItem listViewItem = listViewItemList.get(position);
        iconImageView.setImageDrawable(listViewItem.getImageDrawable());
        titleTextView.setText(listViewItem.getTitleStr());
        descTextView.setText(listViewItem.getAddressStr());

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public void addItem(Drawable icon, String title, String desc){
        HealingListItem item = new HealingListItem();

        item.setImageDrawable(icon);
        item.setTitleStr(title);
        item.setAddressStr(desc);

        listViewItemList.add(item);
    }
}