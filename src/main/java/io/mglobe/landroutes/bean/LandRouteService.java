package io.mglobe.landroutes.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mglobe.landroutes.client.RoutingClient;
import io.mglobe.landroutes.model.LandRouteModel;
import io.mglobe.landroutes.model.ResponseWrapper;
import io.mglobe.landroutes.utils.LandRouteProps;

@Service
public class LandRouteService {
	public final Logger LOG = LogManager.getLogger(RoutingClient.class);
	String timelog = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date());
	public static HashMap<String, List<String>> simpleQueue = new HashMap<>();

	@Autowired
	LandRouteProps landRouteProps;

	public LandRouteProps getConfigProps() {
		return landRouteProps;
	}

	public ResponseWrapper processRoutingRequest(String origin, String destination) {
		ResponseWrapper wrapper = new ResponseWrapper();
		if (simpleQueue == null || simpleQueue.isEmpty()) {
			simpleQueue = new RoutingClient(landRouteProps).sendGetRequest();
		}
		Set<String> originSet = new HashSet<>();
		Set<String> destinationSet = new HashSet<>();

		if (simpleQueue.get(origin) != null || !simpleQueue.get(origin).isEmpty()) {
			List<String> originBorder = simpleQueue.get(origin);
			for (String i : originBorder) {
				originSet.add(i);
			}

		}
		if (simpleQueue.get(destination) != null || !simpleQueue.get(destination).isEmpty()) {
			List<String> destBorder = simpleQueue.get(destination);
			for (String i : destBorder) {
				destinationSet.add(i);
			}
		}

		originSet.retainAll(destinationSet);
		String commonRoute = originSet + "";
		String finalRoute = origin + ", " + commonRoute.substring(1, commonRoute.length() - 1).trim() + ", "
				+ destination;
		List<String> originRoute = new ArrayList<String>(Arrays.asList(finalRoute.split("[\\s,]+")));

		wrapper.setRoute(originRoute);
		return wrapper;
	}

}
