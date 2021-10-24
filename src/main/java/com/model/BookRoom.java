package com.model;


public class BookRoom {
	
  private String userName;   //用户名
  private String location;   //房间号
  private String bookDay;    //预定日期
  private String outDay;    //退房日期
  private int day;       //预定天数
  private String roomState;   //房间的状态
  public BookRoom() {
	super();
	// TODO Auto-generated constructor stub
}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBookDay() {
		return bookDay;
	}

	public void setBookDay(String bookDay) {
		this.bookDay = bookDay;
	}

	public String getOutDay() {
		return outDay;
	}

	public void setOutDay(String outDay) {
		this.outDay = outDay;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getRoomState() {
		return roomState;
	}

	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}
}
