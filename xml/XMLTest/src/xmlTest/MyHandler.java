package xmlTest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{
	
	@Override
	public void startDocument() throws SAXException {

		System.out.println("start document");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		System.out.println("start element: " + qName);
		//System.out.println("attribute: " + attributes.);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		System.out.println("characters " + start + " to " +
				(start + length - 1) +  ": " + new String(ch, start, length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		System.out.println("end element: " + qName);
	}
	
	@Override
	public void endDocument() throws SAXException {

		System.out.println("end document");
	}
}
