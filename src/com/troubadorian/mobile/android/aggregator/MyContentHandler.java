package com.troubadorian.mobile.android.aggregator;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler
{

    // Fields
    private boolean in_outertag = false;

    private boolean in_innertag = false;

    private boolean in_mytag = false;

    
    private MyParsedDataSet myParsedDataSet = new MyParsedDataSet();
    
    
    
    
    /*
     * Getter and Setter
     */
    
    
    public MyParsedDataSet getParsedData()
    {
        return this.myParsedDataSet;
    }
    
    
    
    /*
     * Methods
     * (non-Javadoc)
     * @see 
     * 
     * org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, 
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    
    
    /**
     * Gets to be called on opening tags like <tag>
     */
    
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) 
    throws SAXException
    {
        if (localName.equals("outline"))
        {
            this.in_outertag = true;
        }
        else if (localName.equals("outline"))
        {
            this.in_innertag = true;
        }
        else if (localName.equals("mytag"))
        {
            this.in_mytag = true;
        }
        

    }

    
    /**
     * Gets to be called on closing tags like </tag>
     */
   
    @Override
    public void endElement (String namespaceURI, String localName, String qName) 
    throws SAXException
    {
        if (localName.equals("outertag"))
        {
            this.in_outertag = false;
        }
        
        else if (localName.equals("innertag"))
        {
            
            this.in_innertag = false;
            
        }
        
        else if (localName.equals("mytag"))
        {
            this.in_mytag = false;
        }
        
        else if (localName.equals("tagwithnumber"))
        {
            // Nothing to do here 
        }
    }
    
    /* To be called on the following structure 
     * <tag>characters</tag>
     */
    
    @Override 
    public void characters (char ch[], int start, int length)
    {
        if (this.in_mytag)
        {
            myParsedDataSet.setExtractedString(new String(ch,start,length));
            
        }
    }
    

    
}
