package com.jtidy.scrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.parse.Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;

public class Scrapper implements Parser {
	
	private static Tidy tidy;
	private Configuration conf;
	
	private Tidy GetTidy(){
		if (this.tidy != null) {
			return this.tidy;
		}
		Tidy tidy = new Tidy();
		tidy.setXHTML(true); 
		tidy.setShowErrors(0);
		tidy.setQuiet(true);
		tidy.setShowWarnings(false);
		this.tidy = tidy;
		return this.tidy;
	}
	
	private HashMap<String,String> Scrape(InputStream in) {
		try{
			
			Tidy tidy = GetTidy();
			Document document = tidy.parseDOM(in, null);
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			HashMap<String,String> map = new HashMap<String, String>();
			
			//*[@id="overview-top"]/h1/span[1]
			Element movie = (Element) xpath.compile("//*[@id='overview-top']/h1/span[1]").evaluate(document, XPathConstants.NODE);
			map.put("movie", movie.getFirstChild().getNodeValue());
			
			//*[@id="overview-top"]/div[3]/div[3]/strong/span
			Element rating = (Element) xpath.compile("//*[@id='overview-top']/div[3]/div[3]/strong/span").evaluate(document, XPathConstants.NODE);
			map.put("rating", rating.getFirstChild().getNodeValue());

			//*[@id="overview-top"]/div[4]/a/span
			Element director = (Element) xpath.compile("//*[@id='overview-top']/div[4]/a/span").evaluate(document, XPathConstants.NODE);
			map.put("director", director.getFirstChild().getNodeValue());

			return map;
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ParseResult getParse(Content content) {
		InputStream in = new ByteArrayInputStream(content.getContent());
		HashMap<String,String> map = Scrape(in);
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ParseResult result = new ParseResult(content.getUrl());

		for (String key : map.keySet()) {
			result.put(key,new ParseText(map.get(key)),new ParseData());
		}
		
		return result;
		
	}
	
	public Configuration getConf() {
	    return conf;
	  }

	  public void setConf(Configuration conf) {
	    this.conf = conf;
	  }

}
