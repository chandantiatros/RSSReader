package com.chandan.RSSReader.model;

import java.util.ArrayList;
import java.util.List;

public class FeedData {

	public String name;
	public int id;
	public String address;
	public List<FeedData> entries = new ArrayList<FeedData>();

	public List<FeedData> getMessages() 
	{
		return entries;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString()
	{
		return "Feed Data[ID = "+String.valueOf(id)+", NAME = "+name+",ADDRESS = "+address+"]";

	}

}
