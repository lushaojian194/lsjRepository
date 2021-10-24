package com.model;


public class Room {
    private String location; //房间名
    private int roomPassword; //密码
    private int price; //价格
    private String roomType;//房间类型
    private String introduce;//介绍
    private String picture; //图片路径
    private String roomState;//房间状态
    private int times; //入住次数

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(int roomPassword) {
        this.roomPassword = roomPassword;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
