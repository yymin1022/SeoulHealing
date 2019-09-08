package com.defcon.seoulhealing;

import android.graphics.drawable.Drawable;

public class HealingListItem{
    private Drawable imageDrawable;
    private String titleStr;
    private String addressStr;
    private String themeStr;

    public HealingListItem(Drawable imageDrawable, String titleStr, String addressStr, String themeStr){
        this.imageDrawable = imageDrawable;
        this.titleStr = titleStr;
        this.addressStr = addressStr;
        this.themeStr = themeStr;
    }

    public void setImageDrawable(Drawable icon){
        imageDrawable = icon;
    }
    public void setTitleStr(String title){
        titleStr = title;
    }
    public void setAddressStr(String desc){
        addressStr = desc;
    }
    public void setThemeStr(String theme){
        themeStr = theme;
    }

    public Drawable getImageDrawable(){
        return this.imageDrawable;
    }
    public String getTitleStr(){
        return this.titleStr;
    }
    public String getAddressStr(){
        return this.addressStr;
    }
    public String getThemeStr(){
        return this.themeStr;
    }
}
