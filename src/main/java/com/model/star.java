package com.model;

public class star {
   private String namee;
   private String roomType;
   private String outDay;
   private int starLight;   //亮的星星
   private int starDark;    //暗的星星
   private String evaluate;  //评价

   public star(){
       super();
   }
    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getOutDay() {
        return outDay;
    }

    public void setOutDay(String outDay) {
        this.outDay = outDay;
    }

    public int getStarLight() {
        return starLight;
    }

    public void setStarLight(int starLight) {
        this.starLight = starLight;
    }

    public int getStarDark() {
        return starDark;
    }

    public void setStarDark(int starDark) {
        this.starDark = starDark;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
