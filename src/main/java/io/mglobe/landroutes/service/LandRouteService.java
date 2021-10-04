package io.mglobe.landroutes.service;

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
import io.mglobe.landroutes.model.ResponseWrapper;
import io.mglobe.landroutes.utils.LandRouteProps;

@Service
public class LandRouteService {
	public final Logger LOG = LogManager.getLogger(RoutingClient.class);
	String timelog = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date());
	public static HashMap<String, List<String>> simpleQueue = new HashMap<>();
// initializing configuration properties
	@Autowired
	LandRouteProps landRouteProps;

	public LandRouteProps getConfigProps() {
		return landRouteProps;
	}
//request processor function
	public ResponseWrapper processRoutingRequest(String origin, String destination) {
		ResponseWrapper wrapper = new ResponseWrapper();
		//validate if the queue is empty, if true, make API request to fetch border data. Otherwise, proceed to process the request
		if (simpleQueue == null || simpleQueue.isEmpty()) {
			simpleQueue = new RoutingClient(landRouteProps).sendGetRequest();
		}
		//request processing.
		Set<String> originSet = new HashSet<>(); // border list set for the origin parameter
		Set<String> destinationSet = new HashSet<>(); // border list set for the destination parameter
		
		//loop the queue to fetch border list for both 'origin' and 'destination' parameter values and add to the respective sets
		try {
			//fetch border list for the origin parameters and add to origin set
			if (simpleQueue.get(origin) != null || !simpleQueue.get(origin).isEmpty()) {
				List<String> originBorder = simpleQueue.get(origin);
				for (String i : originBorder) {
					originSet.add(i);
				}

			}
		      //fetch border list for the destination parameters and add to origin set
			if (simpleQueue.get(destination) != null || !simpleQueue.get(destination).isEmpty()) {
				List<String> destBorder = simpleQueue.get(destination);
				for (String i : destBorder) {
					destinationSet.add(i);
				}
			}
		} catch (Exception e) {
			LOG.error(timelog + " : NOT ROUTES FOUND " + e.getMessage());
		}
		
		originSet.retainAll(destinationSet); //find the union of the origin set and destination set.
		String commonRoute = originSet + "";
		String finalRoute = origin + ", " + commonRoute.substring(1, commonRoute.length() - 1).trim() + ", "
				+ destination; // concatenate the origin value, union values of the 2 sets and destination value separated by comma(,) and space
		List<String> originRoute = new ArrayList<String>(Arrays.asList(finalRoute.split("[\\s,]+"))); // create new string list from the string above

		wrapper.setRoute(originRoute); //wrap the result in a response wrapper
		return wrapper; // return the wrapped result.
	}

}
