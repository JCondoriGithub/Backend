package xmlTest;

import org.w3c.dom.*;

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

public class MainDom {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {

		//factory pattern per construire un documento dal parsing di agendadtd.xml
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("agendadtd.xml");
		
		//estrarre il nodo root
		Element root = doc.getDocumentElement();

		print(root, "");
	    
		//factory pattern per estrarre il Transformer
	    Transformer trans = TransformerFactory.newInstance().newTransformer();
	    //si costruisce un nuovo documento xml dalla rappresentazione in doc 
	    trans.transform(new DOMSource(doc), new StreamResult(System.out));

	}

	public static void print(Node n, String spaces) {
		
	    if (n == null) return;
	    if (n instanceof Element) {
	      String s = spaces + n.getNodeName() + " (";
	      NamedNodeMap map = n.getAttributes();
	      if (map != null)
	    	  for (int i = 0; i < map.getLength(); i++)
	    		  s += map.item(i).getNodeName() + "=" + map.item(i).getNodeValue() + " ";
	      s += ")";
	      System.out.println(s);
	    } else  if (n instanceof Text)
	    	System.out.println(spaces + n.getNodeValue());
	    NodeList children = n.getChildNodes();
	    for (int i = 0; i < children.getLength(); i++)
	    	print(children.item(i), spaces + " - ");

	}
}
