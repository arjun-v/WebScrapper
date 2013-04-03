package com.jtidy.scrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class Scrapper {

	public static void main(String[] args) {
		try{
			
			File file = new File("/usr/local/apache-nutch-1.6/dump_folder2/test");
			InputStream in = new FileInputStream(file);
			Tidy tidy = new Tidy();
			tidy.setXHTML(true); 
			tidy.setShowErrors(0);
			tidy.setErrout(null);
			tidy.setQuiet(true);
			Document document = tidy.parseDOM(in, null);
			XPath xpath = XPathFactory.newInstance().newXPath();
			
//			//*[@id="overview-top"]/h1/span[1]
//			//*[@id="overview-top"]/div[3]/div[3]/strong/span
//			//*[@id="overview-top"]/div[4]/a/span
//			//div[@id='leftcontainer']//div[9]//table//tr[4]/td[2]
//			Object title = xpath.compile("//[@id='overview-top']/h1").evaluate(document, XPathConstants.NODE);
//			
//			System.out.println(title);
			 
			NodeList title = (NodeList) xpath.compile("//[@id='overview-top']").evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < title.getLength(); i++) {
			    System.out.println("Answerer: " + title.item(i).getFirstChild().getNodeValue());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
