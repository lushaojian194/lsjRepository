package com.model;

public class Key {

    private String location;   //房间号
    private int roomPassword; //密码

    public Key(String location, int roomPassword) {
        super();
        this.location = location;
        this.roomPassword = roomPassword;
    }

    public Key() {
        super();
        // TODO Auto-generated constructor stub
    }

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


}
