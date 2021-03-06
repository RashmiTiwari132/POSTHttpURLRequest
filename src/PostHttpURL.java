import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;


public class PostHttpURL {
	
	private final String USER_AGENT = "Mozilla/5.0";

	public PostHttpURL() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		PostHttpURL http = new PostHttpURL();
		
		System.out.println("Sent Http POST request");
		http.sendPost();
	}
	
	private void sendPost() throws Exception {
		

		String url = "http://104.199.128.27:8080/japp/auth/test3";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//add request header
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language","en-US,en;q=0.5");
		
		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		JSONObject user = new JSONObject();
		user.put("userId","avikodak");
		user.put("name", "avinash");
		
		//send post request 
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(user.toString());
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request URL : " + url);
		System.out.println("JSON parameters : " + user.toString());
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null){
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println("response" + response.toString());
	}

}
