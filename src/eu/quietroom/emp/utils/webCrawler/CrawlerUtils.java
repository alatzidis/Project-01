package eu.quietroom.emp.utils.webCrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerUtils {
	public static String getString(String stringURL){
		String out = "";
		try {
            URL my_url = new URL(stringURL);
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream(), "UTF-8"));
            String strTemp = "";
            while(null != (strTemp = br.readLine())){
            	out += strTemp + "\n";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return out;
	}
	
	public static String getValidURL(String initURL, String rootURL){
//		System.out.println(initURL);
		initURL = initURL.substring(1, initURL.length() - 1);
//		System.out.println(initURL);
		if(initURL.length() == 0){
			return "";
		}
		if(initURL.substring(0, 1).equals("#")){
			return "";
		}
		if(initURL.length() < 7){
			if(initURL.substring(0, 1).equals("/")){
				return rootURL + initURL;
			}else{				
				return rootURL + "/" + initURL;
			}
		}else{
			if(initURL.substring(0, 7).equals("http://")){
				return initURL;
			}else{				
				if(initURL.substring(0, 1).equals("/")){
					return rootURL + initURL;
				}else{				
					return rootURL + "/" + initURL;
				}
			}
		}
	}
	
	public static String getRootURL(String initURL){
		int httpStringLength = 7;
//		int index = initURL.substring(httpStringLength, initURL.length()).lastIndexOf("/");
		int index = initURL.substring(httpStringLength, initURL.length()).indexOf("/");
		if(index > -1){
			return initURL.substring(0, index + httpStringLength);
		}else{
			return initURL;
		}
	}
	
	public static Set<String> findEmails(String text){
		return find(text, Pattern.compile("([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})"));
	}
	
	public static Set<String> find(String text, Pattern pattern){
		HashSet<String> out = new HashSet<String>();
		Scanner scanner = new Scanner(text);
		while(scanner.hasNext()){
			String line = scanner.next();
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
//				System.out.println("found at line : " + line);
//				System.out.println("the mail : " + matcher.group());
//				System.out.println("the mail : " + line.substring(matcher.start(), matcher.end()));
				out.add(matcher.group());
	        }
		}
		return out;
	}
	
	public static Set<String> getHTMLLinks(final String html, final String rootURL){
		String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
		String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
		Pattern patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
		Pattern patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
		Matcher matcherTag = patternTag.matcher(html);
		Matcher matcherLink;
		HashSet<String> result = new HashSet<String>();
		while(matcherTag.find()){
			String href = matcherTag.group(1); //href
			matcherLink = patternLink.matcher(href);
			while(matcherLink.find()){
				String link = CrawlerUtils.getValidURL(matcherLink.group(1), rootURL); //link
				if(!link.equals("")){
					result.add(link);
				}
			}
		}
		return result;
	}
	
	/*
	public static Vector<HTMLLink> getHTMLLinks(final String html, final String rootURL){
		String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
		String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
		Pattern patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
		Pattern patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
		Matcher matcherTag = patternTag.matcher(html);
		Matcher matcherLink;
		Vector<HTMLLink> result = new Vector<HTMLLink>();
		while(matcherTag.find()){
			String href = matcherTag.group(1); //href
			String linkText = matcherTag.group(2); //link text
			matcherLink = patternLink.matcher(href);
			while(matcherLink.find()){
				String link = CrawlerUtils.getValidURL(matcherLink.group(1), rootURL); //link
				if(!link.equals("")){
					result.add(new HTMLLink(link, linkText));
				}
			}
		}
		return result;
	}
	*/
}
