package xmlTest;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyAgendaHandler extends DefaultHandler {

	private boolean root;
	
	private ArrayList<String> errors = new ArrayList<>();
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	@Override
	public void startDocument() throws SAXException {

		System.out.println("start document");
		errors.clear();
		root = true;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		System.out.println("start element: " + qName);
		if(root && !"agenda".equals(qName)) {
			errors.add("root errata: " + qName);
		}
		root = false;
		
		String eta = attributes.getValue("eta");
		if(eta != null) {
			try {
				if(Integer.parseInt(eta) < 0) {
					errors.add("numero minore di 0: " + eta);
				} else {
					System.out.println("eta: " + eta);
				}
			} catch (Exception e) {
				errors.add("eta non è un numero intero: " + eta);
			}
		} else {
			System.out.println("non ha attributi");
		}
	}
	
	/*@Override
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
	}*/
}
