package xmlTest;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;

public class Main {

	public static void main(String[] args) throws Exception{

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader parser = saxParser.getXMLReader();
        
        ContentHandler handler = new MyAgendaHandler();
        parser.setContentHandler(handler);
        parser.parse("agendadtd.xml");
        
        if(((MyAgendaHandler) handler).getErrors().isEmpty()) {
        	System.out.println("Documento valido!");
        } else {
        	((MyAgendaHandler) handler).getErrors().forEach(e -> System.out.println(e));
        }
        
	}

}
