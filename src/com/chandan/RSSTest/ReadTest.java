package com.chandan.RSSTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chandan.RSSReader.ReadRSS;
import com.chandan.RSSReader.model.FeedData;

public class ReadTest {

	FeedData feed=null;
	ReadRSS parser=null;
	

	@Before
	public void setUp() throws Exception {
		parser = new ReadRSS("file:///home/chandan/workspace/RSSReader/feeds.rss");
		
	}

	@After
	public void tearDown() throws Exception {
		for (FeedData message : feed.getMessages()) {
			System.out.println(message.toString());

		}
	}

	@Test (timeout=1000)
	public void test() {
		feed = parser.readFeed();
	}

}
