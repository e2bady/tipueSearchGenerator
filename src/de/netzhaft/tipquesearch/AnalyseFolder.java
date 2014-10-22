package de.netzhaft.tipquesearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class AnalyseFolder {
	private static final Logger LOG = LoggerFactory.getLogger(AnalyseFolder.class);
	
	public static void main(String[] args) {
		if(args.length < 6) {
			printUsage();
			return;
		}
		Parameter p = Parameter.parse(args);
		List<Map<String, String>> lst = analyseFiles(p);
		write(lst,p.getOutputfile());
	}

	public static List<Map<String, String>> analyseFiles(Parameter p) {
		List<Map<String, String>> lst = new LinkedList<>();
		
		File folder = new File(p.getFoldername());
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        //LOG.error("File " + listOfFiles[i].getName());
		        try {
		        	Map<String, String> m = analyseFile(p, listOfFiles, i);
					
					lst.add(m);
		        } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    }
		return lst;
	}

	public static Map<String, String> analyseFile(Parameter p,
			File[] listOfFiles, int i) throws IOException {
		Map<String, String> m = new HashMap<>();
		Document parse = Jsoup.parse(new File(p.getFoldername() + listOfFiles[i].getName()), "UTF-8");
		m.put("title", parse.select(p.getTitleSelector()).text());
		m.put("text", parse.select(p.getDescSelector()).attr("content") + " - " + getPlainText(parse.select(p.getContentSelector())));
		m.put("tags", getPlainText(parse.select(p.getTagSelector())));
		m.put("loc", listOfFiles[i].getName());
		return m;
	}

	public static void write(List<Map<String, String>> lst, String outputfile) {
		String json = new Gson().toJson(lst);
		PrintWriter out;
		try {
			out = new PrintWriter(new File(outputfile));
			 out.print("var tipuesearch = {\"pages\": " + json+ "};");
			 out.flush();
			 out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getPlainText(Elements select) {
		StringBuilder sb = new StringBuilder();
		HtmlToPlainText htmlToPlainText = new HtmlToPlainText();
		for(Element e : select) {
			sb.append(htmlToPlainText.getPlainText(e));
		}
    	return sb.toString();
	}

	public static void printUsage() {
		LOG.error("Needs 5 arguments. Usage: cmd titleCSSSelector DescriptionCSSSelector ContentCSSSelector TagCSSSelector folder");
	}
}
