package com.zhwl.plugin;



import lombok.Data;

import java.util.HashMap;

/**
 * 分页类
 * @author liangzhilin
 * 创建时间：2014年6月28日
 */
public class Page{
	private Boolean isQuery = false;
	private int size; //每页显示记录数
	private int page;	//当前页
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentResult;	//当前记录起始索引
	private Object data;//存放查询结果
	private HashMap pd = new HashMap();//存放查询条件

	private boolean associativePage;//关联查询分页标志，不支持关联查询分页中包含子查询，否则出现异常

	public boolean getAssociativePage() {
		return associativePage;
	}

	public void setAssociativePage(boolean associativePage) {
		this.associativePage = associativePage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HashMap getPd() {
		return pd;
	}

	public void setPd(HashMap pd) {
		this.pd = pd;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Page(){
		try {
			this.size = 20;
			this.page = 1;
		} catch (Exception e) {
			this.size = 15;
			this.page = 1;
		}
	}

	public Page(int page, int size){
		this.page = page;
		this.size = size;
	}

	public int getTotalPage() {
		if(totalResult%size==0)
			totalPage = totalResult/size;
		else
			totalPage = totalResult/size+1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.isQuery = true;        //pagePlugs查询完后setTotalResult，所以加多这个属性，以便微服务调用不会受到page>getTotalPage()条件影响
		this.totalResult = totalResult;
	}

	public int getPage() {
		if(page<=0)
			page = 1;
		if(isQuery && page>getTotalPage())
			page = getTotalPage();
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCurrentResult() {
		currentResult = (getPage()-1)*getSize();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	@Data
	public static class MainPageSqlInfo{
		Page page;
		String SourceSql;
		String mainPageSql;
		Integer mainPageStartPosition;
		Integer mainPageEndPosition;
	}
}
