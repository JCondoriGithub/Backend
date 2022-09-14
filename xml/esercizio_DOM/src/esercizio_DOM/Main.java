package esercizio_DOM;

import org.w3c.dom.*;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.*;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		//factory pattern per estrarre il parser e iniziare il parsing del docuemento
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("agenda.xml");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Scegli una di queste 2 opzioni: \n 1) stampa documento\n 2) aggiungi persona\n 3) esci dal programma");
		String opzione = scanner.nextLine();
		
		if(opzione.equals("stampa documento")) {
			
		    Transformer trans = TransformerFactory.newInstance().newTransformer();
		    trans.transform(new DOMSource(doc), new StreamResult(System.out));
		
		}else if (opzione.equals("aggiungi persona")) {
			
			Element root = doc.getDocumentElement();
			Element per = doc.createElement("tns:persona");
			per.setAttribute("nome", "Imilla");
			per.setAttribute("cognome", "Condori");
			per.setAttribute("eta", "3");
			per.setAttribute("id", "idvalue2");
			Element des = doc.createElement("tns:descrizione");
			per.appendChild(des);
			Element cv = doc.createElement("tns:cv");
			des.appendChild(cv);
			Element ami = doc.createElement("tns:amici");
			per.appendChild(ami);
			Element per2 = doc.createElement("tns:persona");
			ami.appendChild(per2);
			Element of = doc.createElement("tns:of");
			per2.appendChild(of);
			of.appendChild(doc.createTextNode("idvalue1"));
			root.appendChild(per);

			doc.normalize();
			
		    Transformer trans = TransformerFactory.newInstance().newTransformer();
		    trans.transform(new DOMSource(doc), new StreamResult(System.out));
		
		} else {
			System.out.println("Programma terminato");
		}
	}

}
