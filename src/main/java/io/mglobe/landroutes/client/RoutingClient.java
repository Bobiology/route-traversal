package io.mglobe.landroutes.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import io.mglobe.landroutes.model.LandRouteModel;
import io.mglobe.landroutes.utils.LandRouteProps;
import io.mglobe.landroutes.utils.RestTemplateConfig;

public class RoutingClient {
	public final Logger LOG = LogManager.getLogger(RoutingClient.class);
	String timelog = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date());

	static private Gson gson = new Gson();
	
	
	LandRouteProps routeConfig;
	public RoutingClient(LandRouteProps routeConfig) {
		this.routeConfig = routeConfig;
	}
	
	@Autowired
	private RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
	
	public LandRouteModel[] sendGetRequest(){
		    String endpoint = routeConfig.getEndpoint();

		    StringBuilder response = new StringBuilder();
			try {
				URL url = new URL(endpoint);
				URLConnection urlCon = url.openConnection();
				HttpURLConnection httpCon = (HttpURLConnection) urlCon;
				httpCon.setRequestMethod("GET");
				httpCon.setRequestProperty("Accept", "application/json");
				httpCon.setRequestProperty("Content-Type", "application/json");

	// receive response
				int responseCode = httpCon.getResponseCode();
				switch (responseCode) {
				case 200:
	//case 200, 201:
					InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					String line;
					while ((line = br.readLine()) != null) {
						response.append(line);
					}
					LandRouteModel[] data = gson.fromJson(response.toString(), LandRouteModel[].class);
					LOG.info(timelog+" : TRAVERSAL RESPONSE = "+Arrays.deepToString(data));
					return data;
				default:
					isr = new InputStreamReader(httpCon.getErrorStream());
					br = new BufferedReader(isr);
					while ((line = br.readLine()) != null) {
						response.append(line);
					}
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
	}
}
