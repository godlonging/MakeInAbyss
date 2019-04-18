package com.trip.animaljie.makeinabyss.InfoListViewAdapter;

public class InfoBean {
    private int imageID;
    private String textID;

    public InfoBean(int imageID,String textID){
        this.imageID=imageID;
        this.textID=textID;
    }
    public int getImageID(){
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTextID(){
        return textID;
    }

    public void setTextID(String textID) {
        this.textID = textID;
    }
}
