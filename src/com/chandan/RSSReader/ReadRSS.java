package com.chandan.RSSReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import com.chandan.RSSReader.model.FeedData;

public class ReadRSS {

	final URL url;

	public ReadRSS(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public FeedData readFeed() {
		FeedData feed = new FeedData();
		try {

			final String ID="id";
			final String NAME="name";
			final String ADDRESS="address";

			String id = "";
			String name = "";
			String address = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName()
							.getLocalPart();
					switch (localPart) {

					case ID:
						id = getCharacterData(event, eventReader);
						break;
					case NAME:
						name = getCharacterData(event, eventReader);
						break;
					case ADDRESS:
						address = getCharacterData(event, eventReader);
						break;

					}
				} else if (event.isEndElement()) {

					if (event.asEndElement().getName().getLocalPart().equals(ADDRESS)) {
						FeedData message = new FeedData();
						message.setId(Integer.parseInt(id));
						message.setAddress(address);
						message.setName(name);

						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
			throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
