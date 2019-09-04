package com.defcon.seoulhealing;

import android.graphics.drawable.Drawable;

public class HealingListItem {
    private Drawable imageDrawable ;
    private String titleStr ;
    private String addressStr ;

    public void setImageDrawable(Drawable icon) {
        imageDrawable = icon ;
    }
    public void setTitleStr(String title) {
        titleStr = title ;
    }
    public void setAddressStr(String desc) {
        addressStr = desc ;
    }

    public Drawable getImageDrawable() {
        return this.imageDrawable ;
    }
    public String getTitleStr() {
        return this.titleStr ;
    }
    public String getAddressStr() {
        return this.addressStr ;
    }
}
