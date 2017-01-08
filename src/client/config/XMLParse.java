/*REFERENCES:
 * 	https://www.youtube.com/watch?v=HfGWVy-eMRc
 * https://docs.oracle.com/en/
 */

package client.config;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class XMLParse implements Parsator {
	
	private Conf conf = new Conf();
	
	public XMLParse(Conf conf){
		this.conf = conf;
	}//constructor
	
	public void parse() throws Throwable {
		
		
		//Defines a factory API that enables applications to obtain a parser 
		//that produces DOM object trees from XML documents.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//Defines the API to obtain DOM Document instances from an XML document.
		DocumentBuilder db = dbf.newDocumentBuilder();		
		//The Document interface represents the entire HTML or XML document. 
		//Conceptually, it is the root of the document tree, and provides the
		//primary access to the document's data.
		Document doc = db.parse(conf.getConfigFile());
		
		Element root = doc.getDocumentElement();//ROOT of Tree
		NodeList kids = root.getChildNodes(); // get the kids 
		
		for(int i =0; i < kids.getLength(); i++){
			Node tmp = kids.item(i);//if the node is an element then cast it to one
			
			//assign the private variables of conf values based off the following
			if(tmp instanceof Element){
				Element el = (Element) tmp;
				if(el.getNodeName().equals("host")){
					conf.setHost(el.getFirstChild().getNodeValue());
				}//if its host then set the value
				else if(el.getNodeName().equals("port")){
					conf.setPort(Integer.parseInt(el.getFirstChild().getNodeValue()));
				}//if its port then set its value
				else if(el.getNodeName().equals("dir")){
					conf.setDir(el.getFirstChild().getNodeValue());
				}//if its directory set its value 
			}//if	
		}//for loop
	}//parse method

	public Conf getConf() {
		return conf;
	}

	public void setConf(Conf conf) {
		this.conf = conf;
	}

}//class
