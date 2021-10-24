package com.model;

public class Money {
  private String userName;  //用户名
  private int balance;     //余额
  
public Money(String userName, int balance) {
	super();
	this.userName = userName;
	this.balance = balance;
}

public Money() {
	super();
	// TODO Auto-generated constructor stub
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public int getBalance() {
	return balance;
}

public void setBalance(int balance) {
	this.balance = balance;
}
  
  
}
