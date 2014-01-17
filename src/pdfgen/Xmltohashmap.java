package pdfgen;

import java.io.File; 
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pdfgen.Pdfreaderparser;
public class Xmltohashmap {
	public static HashMap<String,String>hmap=new HashMap<String,String>();
	public void storexml(String xml_filepath)throws ParserConfigurationException, SAXException, IOException
	{

		File fxml=new File(xml_filepath);
		DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder=dbfactory.newDocumentBuilder();
		Document doc=dbuilder.parse(fxml);
		doc.getDocumentElement().normalize();
		//hmap.put(doc.getDocumentElement().getNodeName(), "1");
		NodeList nlist=doc.getElementsByTagName("Answer");
		System.out.println(nlist.getLength());
		Element e=doc.getDocumentElement();
		
		for(int temp=0;temp<nlist.getLength();temp++)
		{
			Node nNode=(Node) nlist.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eelement=(Element)nNode;
				String pa=Integer.toString(temp);
			   String da=eelement.getTextContent();
			 //   S=text1.getData();
				hmap.put(pa,da);
				
				
			}
		}
	    System.out.println(hmap);
	    
		
	}

}