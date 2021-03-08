package mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class Bean
 */
@WebServlet("/Bean")
public class Bean {

	//public String URL = "http://www.labs.skanetrafiken.se/v2.2/stationresults.asp?selPointFrKey=80000";
	public String URL = "http://www.labs.skanetrafiken.se/v2.2/stationresults.asp?selPointFrKey=80000";
	//public String URL = "http://www.labs.skanetrafiken.se/v2.2/querystation.asp?inpPointfr=Ystad";


	private ArrayList<String> names = new ArrayList<String>();;

	public ArrayList<String> getNames() {
		return names;
	}
	

	
	public void setNames() {

		// fill the names list with stuff
		try {
			apiGet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	private void apiGet() throws IOException {

		URL line_api_url = new URL(URL);

		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();

		linec.setDoInput(true);

		linec.setDoOutput(true);

		linec.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		String inputLine;

		String ApiResponse = "";

		while ((inputLine = in.readLine()) != null) {

			ApiResponse += inputLine;

		}
		in.close();
		System.out.println(ApiResponse);

		Document doc = convertStringToXMLDocument(ApiResponse);

		doc.getDocumentElement().normalize();

		System.out.println("Root ele:" + doc.getDocumentElement().getNodeName());

		Node nodeBody = doc.getElementsByTagName("soap:Body").item(0);

		NodeList nodeResult = (NodeList) nodeBody.getFirstChild().getFirstChild();

		Node nodelines = nodeResult.item(2);

		NodeList listOflines = nodelines.getChildNodes();

		for (int i = 0; i < listOflines.getLength(); i++) {

			// System.out.println(listOflines.item(i).getFirstChild().getTextContent());

			NodeList allLine = listOflines.item(i).getChildNodes();

			for (int y = 0; y < allLine.getLength(); y++) {
				// System.out.println(allLine.item(y).getTextContent());

				if (allLine.item(y).getNodeName().equals("Towards")) {

					System.out.println("Name " + allLine.item(y).getTextContent());

					// add xml result to list
					names.add(allLine.item(y).getTextContent());
					
				}

			}
		}

	}

	private Document convertStringToXMLDocument(String xmlString) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;

		try {

			builder = factory.newDocumentBuilder();

			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

			return doc;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
