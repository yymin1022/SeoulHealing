package com.defcon.seoulhealing;

import android.graphics.drawable.Drawable;

public class HealingListItem{
    private Drawable imageDrawable;
    private String titleStr;
    private String addressStr;
    private String themeStr;
    private String contentIDStr;

    public HealingListItem(Drawable imageDrawable, String titleStr, String addressStr, String themeStr, String contentIDStr){
        this.imageDrawable = imageDrawable;
        this.titleStr = titleStr;
        this.addressStr = addressStr;
        this.themeStr = themeStr;
        this.contentIDStr = contentIDStr;
    }

    public void setImageDrawable(Drawable icon){
        imageDrawable = icon;
    }
    public void setAddressStr(String desc){
        addressStr = desc;
    }
    public void setContentIDStr(String contentID){
        contentIDStr = contentID;
    }
    public void setThemeStr(String theme){
        themeStr = theme;
    }
    public void setTitleStr(String title){
        titleStr = title;
    }

    public Drawable getImageDrawable(){
        return this.imageDrawable;
    }
    public String getAddressStr(){
        return this.addressStr;
    }
    public String getContentIDStr(){
        return this.contentIDStr;
    }
    public String getThemeStr(){
        return this.themeStr;
    }
    public String getTitleStr(){
        return this.titleStr;
    }
}
