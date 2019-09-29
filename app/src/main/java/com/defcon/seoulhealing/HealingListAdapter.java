package com.defcon.seoulhealing;

import android.content.Context;
import android.graphics.Typeface;
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
        TextView themeTextView = convertView.findViewById(R.id.list_item_theme);
        Typeface textFont = Typeface.createFromAsset(context.getAssets(), "fonts/font_namsan.ttf" );

        HealingListItem listViewItem = listViewItemList.get(position);
        iconImageView.setImageDrawable(listViewItem.getImageDrawable());
        titleTextView.setText(listViewItem.getTitleStr());
        themeTextView.setText(listViewItem.getThemeStr());

        titleTextView.setTypeface(textFont);
        themeTextView.setTypeface(textFont);

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

    public void addItem(Drawable icon, String title, String theme, String themeID, String contentID){
        HealingListItem item = new HealingListItem(icon, title, theme, themeID, contentID);

        item.setImageDrawable(icon);
        item.setContentIDStr(contentID);
        item.setThemeStr(theme);
        item.setThemeIDStr(themeID);
        item.setTitleStr(title);

        listViewItemList.add(item);
    }
}