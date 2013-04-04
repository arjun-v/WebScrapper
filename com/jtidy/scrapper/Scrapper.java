package com.jtidy.scrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.tidy.Tidy;

public class Scrapper {

	public static void main(String[] args) {
		try{
			
			File file = new File("/usr/local/apache-nutch-1.6/dump_folder2/test");
			InputStream in = new FileInputStream(file);
			Tidy tidy = new Tidy();
			tidy.setXHTML(true); 
			tidy.setShowErrors(0);
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			Document document = tidy.parseDOM(in, null);
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			//*[@id="overview-top"]/h1/span[1]
			Element itemprop = (Element) xpath.compile("//*[@id='overview-top']/h1/span[1]").evaluate(document, XPathConstants.NODE);
			System.out.print(itemprop.getFirstChild().getNodeValue() + "\t");
			
			//*[@id="overview-top"]/div[3]/div[3]/strong/span
			Element rating = (Element) xpath.compile("//*[@id='overview-top']/div[3]/div[3]/strong/span").evaluate(document, XPathConstants.NODE);
			System.out.print(rating.getFirstChild().getNodeValue() + "\t");

			//*[@id="overview-top"]/div[4]/a/span
			Element director = (Element) xpath.compile("//*[@id='overview-top']/div[4]/a/span").evaluate(document, XPathConstants.NODE);
			System.out.print(director.getFirstChild().getNodeValue() + "\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
