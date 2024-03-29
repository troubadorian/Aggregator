package com.troubadorian.mobile.android.aggregator;

import java.util.List;
import java.util.Vector;

public class MyContentFeed {
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubdate = null;
	private List<MyContentItem> itemList;
	
	MyContentFeed(){
		itemList = new Vector<MyContentItem>(0);
	}
	
	void addItem(MyContentItem item){
		itemList.add(item);
	}
	
	MyContentItem getItem(int location){
		return itemList.get(location);
	}
	
	List<MyContentItem> getList(){
		return itemList;
	}
	
	void setTitle(String value)
	{
		title = value;
	}
	void setDescription(String value)
	{
		description = value;
	}
	void setLink(String value)
	{
		link = value;
	}
	void setPubdate(String value)
	{
		pubdate = value;
	}
	
	String getTitle()
	{
		return title;
	}
	String getDescription()
	{
		return description;
	}
	String getLink()
	{
		return link;
	}
	String getPubdate()
	{
		return pubdate;
	}

}
