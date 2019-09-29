package com.defcon.seoulhealing;

import android.graphics.drawable.Drawable;

public class HealingListItem{
    private Drawable imageDrawable;
    private String titleStr;
    private String themeStr;
    private String themeIDStr;
    private String contentIDStr;

    public HealingListItem(Drawable imageDrawable, String titleStr, String themeStr, String themeIDStr, String contentIDStr){
        this.imageDrawable = imageDrawable;
        this.titleStr = titleStr;
        this.themeStr = themeStr;
        this.themeIDStr = themeIDStr;
        this.contentIDStr = contentIDStr;
    }

    public void setImageDrawable(Drawable icon){
        imageDrawable = icon;
    }
    public void setContentIDStr(String contentID){
        contentIDStr = contentID;
    }
    public void setThemeStr(String theme){
        themeStr = theme;
    }
    public void setThemeIDStr(String themeID){
        themeIDStr = themeID;
    }
    public void setTitleStr(String title){
        titleStr = title;
    }

    public Drawable getImageDrawable(){
        return this.imageDrawable;
    }
    public String getContentIDStr(){
        return this.contentIDStr;
    }
    public String getThemeStr(){
        return this.themeStr;
    }
    public String getThemeIDStr(){
        return this.themeIDStr;
    }
    public String getTitleStr(){
        return this.titleStr;
    }
}
