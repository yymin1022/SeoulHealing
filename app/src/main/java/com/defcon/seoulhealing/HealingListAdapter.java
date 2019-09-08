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
    Context context;
    ArrayList<HealingListItem> listViewItemList;

    public HealingListAdapter(Context context, ArrayList<HealingListItem> listViewItemList){
        this.context = context;
        this.listViewItemList = listViewItemList;
    }

    @Override
    public int getCount(){
        return this.listViewItemList.size();
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
        TextView addressTextView = convertView.findViewById(R.id.list_item_address);
        TextView themeTextView = convertView.findViewById(R.id.list_item_theme);

        HealingListItem listViewItem = listViewItemList.get(position);
        iconImageView.setImageDrawable(listViewItem.getImageDrawable());
        titleTextView.setText(listViewItem.getTitleStr());
        addressTextView.setText(listViewItem.getAddressStr());
        themeTextView.setText(listViewItem.getThemeStr());

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return this.listViewItemList.get(position);
    }

    public void addItem(Drawable icon, String title, String address, String theme){
        HealingListItem item = new HealingListItem(icon, title, address, theme);

        item.setImageDrawable(icon);
        item.setTitleStr(title);
        item.setAddressStr(address);
        item.setThemeStr(theme);

        listViewItemList.add(item);
    }
}