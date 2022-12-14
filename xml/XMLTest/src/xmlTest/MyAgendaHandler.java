package xmlTest;

import java.util.ArrayList;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyAgendaHandler extends DefaultHandler {

	private int countCV = 0;
	private HashSet<String> ids = new HashSet<String>();
	private HashSet<String> idrefs = new HashSet<String>();

	private boolean root, inOf, inAmici;
	private boolean inP1, inP2, ofP1, ofP2;
	
	private ArrayList<String> errors = new ArrayList<>();
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	@Override
	public void startDocument() throws SAXException {

		System.out.println("start document");
		errors.clear();
		root = true;
		
		inP1 = inP2 = ofP1 = ofP2 = false;
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
		} 
		
		if(qName.equals("cv")) {
			countCV++;
		}
		
		//si determina se ci si trova nell'elemento "amici"
		inAmici |= qName.equals("amici");
		
		if(qName.equals("persona")) {
			if(attributes.getValue("id") != null) {
				String id = attributes.getValue("id");
				ids.add(id);
			}
			
			//si distingue tra i 2 tipi di "persona" e si stabilisce che:
			//persona2 non deve avere attributi
			if(inAmici && attributes.getLength() != 0)
				errors.add("attributi non ammessi");
			//persona1 non deve avere attributi MANCANTI
			if(!inAmici && (attributes.getValue("id") == null || attributes.getValue("nome") == null || attributes.getValue("cognome") == null))
				errors.add("attributi mancanti");
		
			//si determina se ci si trova in "persona1" o "persona2"
			if(!inP1) {
				inP1 = true;
				inP2 = ofP1 = ofP2 = false;
			} else if(!inP2) {
				inP2 = true;
				ofP2 = false;
			} else
				errors.add("profondita 3");
		}
		
		// si determina se "of" si trova in "persona1" o "persona2"
		if(qName.equals("of")) {
			inOf = true;
			if(inP2)
				ofP2 = true;
			else
				ofP1 = true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		System.out.println("characters: " + new String(ch, start, length));
		
		if(inOf) {
			String idref = new String(ch, start, length);
			idrefs.add(idref);
			inOf = false;
		}
			
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		System.out.println("end element: " + qName);
		
		if(qName.equals("descrizione")) {
			if(countCV != 1) {
				errors.add("numero errato di cv: " + countCV);
			}
			countCV = 0;
		}
		
		//si determina che non ci si trova più nell'elemento amici
		inAmici &= !qName.equals("amici");
		
		if(qName.equals("persona"))
			if(inP2) {
				//errore se in "persona2" non vi è un "of"
				if(!ofP2)
					errors.add("riferimento a persona senza of");
				inP2 = false;
			} else {
				//errore se in "persona1" vi è un "of" 
				if(ofP1)
					errors.add("anagrafica di persona con of");
				inP1 = false;
			}
	}
	
	@Override
	public void endDocument() throws SAXException {

		System.out.println("end document");
		
		idrefs.removeAll(ids);
		idrefs.forEach(e -> errors.add("riferimento insistente: " + e));
	}
}
