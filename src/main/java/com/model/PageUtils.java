//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.model;

import com.model.RoomRecord;

import java.util.List;

public class PageUtils {
	private int currentPageNum;
	private int pageSize;
	private int totalRecords;
	private int totalPageNum;
	private int startIndex;
	private int endIndex;
	private int prePageNum;
	private int nextPageNum;
	private List<RoomRecord> list;
	private int startPage;
	private int endPage;
	private String url;

	public PageUtils(int currentPageNum, int totalRecords, int pageSize) {
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		this.pageSize = pageSize;
		this.startIndex = (currentPageNum - 1) * pageSize;
		this.endIndex = pageSize;
		this.totalPageNum = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
		this.startPage = currentPageNum - 4;
		this.endPage = currentPageNum + 4;
		if (this.totalPageNum > 9) {
			if (this.startPage < 1) {
				this.startPage = 1;
				this.endPage = this.startPage + 8;
			}

			if (this.endPage > this.totalPageNum) {
				this.endPage = this.totalPageNum;
				this.startPage = this.endPage - 8;
			}
		} else {
			this.startPage = 1;
			this.endPage = this.totalPageNum;
		}

	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStartPage() {
		return this.startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return this.endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrePageNum() {
		this.prePageNum = this.currentPageNum - 1;
		if (this.prePageNum < 1) {
			this.prePageNum = 1;
		}

		return this.prePageNum;
	}

	public int getNextPageNum() {
		this.nextPageNum = this.currentPageNum + 1;
		if (this.nextPageNum > this.totalPageNum) {
			this.nextPageNum = this.totalPageNum;
		}

		return this.nextPageNum;
	}

	public int getCurrentPageNum() {
		return this.currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return this.totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPageNum() {
		return this.totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getStartIndex() {
		return this.startIndex;
	}

	public int getEndIndex() {
		return this.endIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public List<RoomRecord> getList() {
		return this.list;
	}

	public void setList(List<RoomRecord> list) {
		this.list = list;
	}
}
