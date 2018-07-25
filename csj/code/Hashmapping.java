

import java.io.*;
import java.util.*;
import java.sql.*;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class Hashmapping {
	
	public static HashMap<String, ProductCatalog> phoneCatalog = null;
	public static HashMap<String, ProductCatalog> laptopCatalog = null;
	public static HashMap<String, ProductCatalog> watchCatalog = null;
	public static HashMap<String, ProductCatalog> speakerCatalog = null;
	public static HashMap<String, ProductCatalog> headphoneCatalog = null;
	public static HashMap<String, ProductCatalog> extStoreCatalog = null;
	public static HashMap<String, ProductCatalog> productCatalog = null;
	public static HashMap<String, ProductCatalog> accCatalog = null;
	
	public static final String ProductXML = "C:/Users/ithak/workspace_ewa/Copy_of_Demo2/WebContent/WEB-INF/ProductCatalog.xml";

	public static HashMap<String, ProductCatalog> isPhoneCatalog() throws IOException {
		if(phoneCatalog == null)
		{			
			phoneCatalog = SAXInterface(ProductXML);
		}		
		return phoneCatalog;
	}
	
	public static HashMap<String, ProductCatalog> isLaptopCatalog() throws IOException {
		if(laptopCatalog == null)
		{			
			laptopCatalog = SAXInterface(ProductXML);
		}		
		return laptopCatalog;
	}
	public static HashMap<String, ProductCatalog> isWatchCatalog() throws IOException {
		if(watchCatalog == null)
		{			
			watchCatalog = SAXInterface(ProductXML);
		}		
		return watchCatalog;
	}
	public static HashMap<String, ProductCatalog> isSpeakerCatalog() throws IOException {
		if(speakerCatalog == null)
		{			
			speakerCatalog = SAXInterface(ProductXML);
		}		
		return speakerCatalog;
	}
	public static HashMap<String, ProductCatalog> isHeadphoneCatalog() throws IOException {
		if(headphoneCatalog == null)
		{			
			headphoneCatalog = SAXInterface(ProductXML);
		}		
		return headphoneCatalog;
	}
	public static HashMap<String, ProductCatalog> isExtStoreCatalog() throws IOException {
		if(extStoreCatalog == null)
		{			
			extStoreCatalog = SAXInterface(ProductXML);
		}		
		return extStoreCatalog;
	}
	public static HashMap<String, ProductCatalog> isAccessoryCatalog() throws IOException {
		if(accCatalog == null)
		{			
			accCatalog = SAXInterface(ProductXML);
		}		
		return accCatalog;
	}

	public static HashMap<String, ProductCatalog> SAXInterface(String filePath)throws IOException
	{
		SAXParserDataStore saxHandler = new SAXParserDataStore();
		HashMap<String, ProductCatalog> pc = new HashMap<String, ProductCatalog>();
		try
		{
			pc = saxHandler.readDataFromXML(filePath);
		}
		catch(SAXException e)
		{
			System.out.println("Error - SAXException");
		}
		catch(ParserConfigurationException e)
		{
			System.out.println("Error - ParserConfigurationException");
		}
		return pc;
	}

	public static HashMap<String, ProductCatalog> getAllProducts() throws IOException {
		if(productCatalog == null)
		{
			productCatalog = new HashMap<String, ProductCatalog>();
			
			productCatalog.putAll(isPhoneCatalog());
			productCatalog.putAll(isWatchCatalog());
			productCatalog.putAll(isLaptopCatalog());
			productCatalog.putAll(isSpeakerCatalog());
			productCatalog.putAll(isHeadphoneCatalog());
			productCatalog.putAll(isExtStoreCatalog());
		}
		return productCatalog;
	}

}
