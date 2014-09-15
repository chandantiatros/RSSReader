package com.chandan.RSSWriter;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.chandan.RSSReader.model.FeedData;

public class WriteRSS {

	private String outputFile;
	private FeedData storeFeed;

	public WriteRSS(FeedData storeFeed, String outputFile) {

		this.outputFile=outputFile;
		this.storeFeed=storeFeed;
	}


	public void writeXML() throws Exception {

		// create a XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

		// create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(outputFile));

		// create a EventFactory

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");

		// create and write Start Tag

		StartDocument startDocument = eventFactory.createStartDocument();

		eventWriter.add(startDocument);

		// create open tag
		eventWriter.add(end);

		StartElement rssStart = eventFactory.createStartElement("", "", "rss");
		eventWriter.add(rssStart);
		eventWriter.add(end);
		eventWriter.add(eventFactory.createStartElement("", "", "student"));
		eventWriter.add(end);


		for (FeedData entry : storeFeed.getMessages()) {
			eventWriter.add(eventFactory.createStartElement("", "", "row"));
			eventWriter.add(end);
			createNode(eventWriter, "id", String.valueOf(entry.getId()));
			createNode(eventWriter, "name", entry.getName());
			createNode(eventWriter, "address", entry.getAddress());
			//eventWriter.add(end);
			eventWriter.add(eventFactory.createEndElement("", "", "row"));
			eventWriter.add(end);

		}

		eventWriter.add(eventFactory.createEndElement("", "", "student"));
		eventWriter.add(end);
		//  eventWriter.add(end);
		eventWriter.add(eventFactory.createEndElement("", "", "rss"));

		eventWriter.add(end);

		eventWriter.add(eventFactory.createEndDocument());

		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name,

			String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
}
