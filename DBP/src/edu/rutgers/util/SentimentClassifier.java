package edu.rutgers.util;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import edu.rutgers.model.Mood;



 public class SentimentClassifier {  
    
    
    public static Mood classify(String text){  
   	
    	//480a12157176e0d0082e0793048b992b35ef5c62
    	//593dd3a1f964215a6fc6d2e38d2f959d1fc7e935
    	//acb333ef66d9ff8268f841e026d2b1ca53860bb2
    	
    	Mood mood=new Mood();
    	try{
    	URL url = new URL("http://access.alchemyapi.com/calls/text/TextGetTextSentiment?apikey=480a12157176e0d0082e0793048b992b35ef5c62&sentiment=1&showSourceText=1&text=" + URLEncoder.encode(text));
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
 
		//add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


		//print result

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = dbf.newDocumentBuilder();
    	Document doc = db.parse(new DataInputStream(con.getInputStream())); 
    	System.out.println("xml"+doc.getTextContent());
    	XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile("//docSentiment");
        Object result = expr.evaluate(doc, XPathConstants.NODE);
        Element results = (Element) result;
        if(results!=null){
        	if(results.getElementsByTagName("type").getLength()!=0){
        		mood.setType(results.getElementsByTagName("type").item(0).getTextContent());
        		if(results.getElementsByTagName("score").item(0)!=null){
        			mood.setScore(Float.parseFloat((results.getElementsByTagName("score").item(0).getTextContent().trim())));
        		}    		
        	}
        }
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	System.out.println(mood);
    	return mood;
    }  
    
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException, JSONException, XPathExpressionException{
    	SentimentClassifier a=new SentimentClassifier();
    	Mood m=a.classify("neutral");
    	System.out.println(m.toString());
  
    
    }
 } 

