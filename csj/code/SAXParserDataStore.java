


import java.io.File;

/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 



The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/





import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SAXParserDataStore extends DefaultHandler {
   // Phone phone;
    //SmartWatch smartWatch;
    ProductCatalog prdCat;
//    static HashMap<String,Phone> phones = new HashMap<String, Phone>();
    public static HashMap<String,ProductCatalog> prodCatalog = new HashMap<String, ProductCatalog>();
//    static HashMap<String,SmartWatch> watches = new HashMap<String, SmartWatch>();
    String xmlFileName;
    String elementValueRead;
    
	boolean prootnode = false;
	boolean pid = false;
	boolean pname = false;
	boolean pretailer = false;
	boolean pprice = false;
	boolean pcondition = false;
	boolean pimage = false;
	boolean pprodType = false;
	boolean pmanufacRebate = false;
	boolean pquantity = false;
	boolean pOnSale= false;
	
	
   /* public SAXParserDataStore(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        phones = new HashMap<String,Phone>();
        parseDocument();
       
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }*/
    
    public HashMap<String,ProductCatalog> readDataFromXML(String filename) throws SAXException, IOException, ParserConfigurationException{
    	SAXParserFactory factory = SAXParserFactory.newInstance();
    	SAXParser parser = factory.newSAXParser();
    	
    	parser.parse(new File(filename), this);
    	
    	return prodCatalog;
			}
			
			
    


    





////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
    	
    	if(elementName.matches("(?i)phone|smartWatch|speaker|laptop|headPhone|externalStorage|accessory"))
		{
			prootnode = true;
			prdCat = new ProductCatalog();
		}
		
    	if(prootnode)
		{		
			if (elementName.equalsIgnoreCase("ID")) {
				pid = true;
			}

			else if (elementName.equalsIgnoreCase("NAME")) {
				pname = true;
			}

			else if (elementName.equalsIgnoreCase("retailer")) {
				pretailer = true;
			}

			else if (elementName.equalsIgnoreCase("PRICE")) {
				pprice = true;
			}

			else if (elementName.equalsIgnoreCase("CONDITION")) {
				pcondition = true;
			}

			else if (elementName.equalsIgnoreCase("image")) {
				pimage = true;
			}
			else if (elementName.equalsIgnoreCase("prodType")) {
				pprodType = true;
			}
			else if (elementName.equalsIgnoreCase("manufacturerRebate")) {
				pmanufacRebate = true;
			}
			else if (elementName.equalsIgnoreCase("quantity")) {
				pquantity = true;
			}
			else if (elementName.equalsIgnoreCase("onSale")) {
				pOnSale = true;
			}
        }
    	
    	/*if(elementName.matches("(?i)smartWatch"))
		{
			swrootnode = true;
			prdCat = new ProductCatalog();
		}
		
    	if(swrootnode)
		{		
			if (elementName.equalsIgnoreCase("ID")) {
				swid = true;
			}

			else if (elementName.equalsIgnoreCase("NAME")) {
				swname = true;
			}

			else if (elementName.equalsIgnoreCase("retailer")) {
				swretailer = true;
			}

			else if (elementName.equalsIgnoreCase("PRICE")) {
				swprice = true;
			}

			else if (elementName.equalsIgnoreCase("CONDITION")) {
				swcondition = true;
			}

			else if (elementName.equalsIgnoreCase("image")) {
				swimage = true;
			}
        }*/

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
    	if(element.matches("(?i)phone|smartWatch|speaker|laptop|headPhone|externalStorage|accessory")) {			
			prodCatalog.put(prdCat.getId(), prdCat);
			prootnode = false;
		}
    	/*else if(element.matches("(?i)smartWatch")) {			
    		prodCatalog.put(prdCat.getId(), prdCat);
			swrootnode = false;
		}*/
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	if (pid) {
    		prdCat.setId(new String(ch, start, length));
			pid = false;
		}
		
		else if (pname) {
			prdCat.setName(new String(ch, start, length));
			pname = false;
		}
		
		else if (pretailer) {
			prdCat.setRetailer(new String(ch, start, length));
			pretailer = false;
		}
		
		else if (pcondition) {
			prdCat.setCondition(new String(ch, start, length));
			pcondition = false;
		}
		
		
		else if (pprice) {
			prdCat.setPrice(new String(ch, start, length));
			pprice = false;
		}
		
		else if (pimage) {
			prdCat.setImage(new String(ch, start, length));
			pimage = false;
		}
		else if (pprodType) {
			prdCat.setProdType(new String(ch, start, length));
			pprodType = false;
		}
		else if (pmanufacRebate) {
			prdCat.setManufacturerRebate(new String(ch, start, length));
			pmanufacRebate = false;
		}
		else if (pquantity) {
			prdCat.setQuantity(new String(ch, start, length));
			pquantity = false;
		}
		else if (pOnSale) {
			prdCat.setOnSale(new String(ch, start, length));
			pOnSale = false;
		}
    	//-----------------------------SmartWatch
    	
}
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////

   


	/*public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");
		new SAXParserDataStore(TOMCAT_HOME+"ProductCatalog.xml");
		
	}*/


