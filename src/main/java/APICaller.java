import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

// https://www.baeldung.com/java-http-request

public class APICaller {
	public final String baseURI;
	public final String authentification;

	public APICaller(String baseURI, String authentification) {
		this.baseURI = baseURI;
		this.authentification = authentification;
	}

	public String FetchJSON(Map<String, String> params) {
		URL url = null;
		
		try {
			url = BuildUrl(params);
		} catch (MalformedURLException e) {
			System.err.println("Url mal former. BuildUrl connaot build the object");
			System.err.println(e.getMessage());
			return "";
		}
		
		HttpURLConnection con = null;
		
		try {
			con = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.err.println("Erreur dans lors de la connexion");
			System.err.println(e.getMessage());
			return "";
		}
		
		try {
			con.setRequestMethod("GET");
			
		} catch (ProtocolException e) {
			System.err.println("Erreur dans lors de l'execution de la requête");
			System.err.println(e.getMessage());
			return "";
		}
		
		String json = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			
			con.disconnect();
			
			json = content.toString();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "";
		}

		return json;
	}
	
	public URL BuildUrl(Map<String, String> params) throws MalformedURLException {
		StringBuilder sb = new StringBuilder();
		sb.append(this.baseURI);
		
		for (Entry<String, String> kp : params.entrySet()) {
			sb.append(kp.getKey());
			sb.append("=");
			sb.append(kp.getValue());
			sb.append("&");
		}
		
		sb.append(this.authentification);
		
		return new URL(sb.toString());
	}
}
