package com.jsoup.scrapper;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Scrapper {

	public static void main(String[] args) {
		try { 

			File file = new File("/usr/local/apache-nutch-1.6/dump_folder2/test");
			Document doc = Jsoup.parse(file, "UTF-8");
			
			//*[@id="overview-top"]/h1/span[1]
			Node title = doc.select("#overview-top h1").first().getElementsByTag("span").first().childNode(0);
			System.out.print(((TextNode)title).text()+"\t");
			
			//*[@id="overview-top"]/div[3]/div[3]/strong/span
			Elements contents = doc.select(".star-box,.giga-star");
			Node rating = contents.first().child(2).getElementsByTag("strong").first().getElementsByTag("span").first().childNode(0);
			System.out.print(((TextNode)rating).text()+"\t");
			
			//*[@id="overview-top"]/div[4]/a/span
			Node director = doc.select("#overview-top").first().getElementsByAttributeValue("itemprop", "director").first().getElementsByTag("a").first().getElementsByTag("span").first().childNode(0);
			System.out.println(((TextNode)director).text()+"\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
