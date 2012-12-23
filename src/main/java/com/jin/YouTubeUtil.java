package com.jin;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class YouTubeUtil {

	private static final String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/videos";
	private static final String QUERY_ARG = "?q=";
	private String PARAMS;
	private List<YouTubeVideo> videos = new ArrayList<YouTubeVideo>();

	public List<YouTubeVideo> search(String query, int limit) {
		if (limit > 0) {
			PARAMS = "&max-results=" + limit;
		}
		try {
			query = URLEncoder.encode(query, "utf-8");
			String url = YOUTUBE_URL + QUERY_ARG + query + PARAMS;
			Document doc = getDocument(url);
			NodeList videoNodes = doc.getElementsByTagName("entry");
			for (int i = 0; i < videoNodes.getLength(); i++) {
				Element video = (Element) videoNodes.item(i);
				String title = getTagValue("title", video);
				String fullId = getTagValue("id", video);
				String id = fullId.replace("http://gdata.youtube.com/feeds/api/videos/", "");
				videos.add(new YouTubeVideo(id, title));
			}
		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return videos;
	}

	private Document getDocument(String url) throws Exception {
		return DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(new URL(url).openStream());
	}

	private static String getTagValue(String tagName, Element element) {
		NodeList nodes = element.getElementsByTagName(tagName).item(0)
				.getChildNodes();
		Node value = (Node) nodes.item(0);
		return value.getNodeValue().trim();
	}
}
