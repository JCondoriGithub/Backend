package xmlTest;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;

public class Main {

	public static void main(String[] args) throws Exception{

		//factory pattern per estrarre il parser
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader parser = saxParser.getXMLReader();
        
        //crerare handler
        ContentHandler handler = new MyAgendaHandler();
        //registrare l'handler al parser
        parser.setContentHandler(handler);
        //si inizia a fare il parsing
        parser.parse("agendadtd.xml");
        
        //gestione degli errori
        if(((MyAgendaHandler) handler).getErrors().isEmpty()) {
        	System.out.println("Documento valido!");
        } else {
        	((MyAgendaHandler) handler).getErrors().forEach(e -> System.out.println(e));
        }
        
	}

}
