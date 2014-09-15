package com.chandan.RSSTest;


import com.chandan.RSSReader.model.FeedData;
import com.chandan.RSSWriter.WriteRSS;

public class WriteTest {

	public static void main(String[] args) {


		FeedData feedData = null;
		FeedData dataStore=new FeedData();
		for(int i=1;i<6;i++)
		{
			feedData = new FeedData();
			feedData.setId(i);
			feedData.setName("Name"+i);
			feedData.setAddress("Address"+i);
			dataStore.getMessages().add(i-1, feedData);
		}
		// now write the file
		WriteRSS writer = new WriteRSS(dataStore, "feeds.rss");
		try {
			writer.writeXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
