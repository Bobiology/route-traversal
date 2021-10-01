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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.mglobe.landroutes.client.RoutingClient;
import io.mglobe.landroutes.model.LandRouteModel;
import io.mglobe.landroutes.model.ResponseWrapper;
import io.mglobe.landroutes.model.RouteModel;
import io.mglobe.landroutes.repository.RouteRepository;
import io.mglobe.landroutes.utils.LandRouteProps;

@Service
public class LandRouteService {
	public final Logger LOG = LogManager.getLogger(RoutingClient.class);
	String timelog = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date());
	// public static HashMap<String, List<String>> simpleQueue = new HashMap<>();
	// @Autowired
	// RouteRepository repository;
	@Autowired
	LandRouteProps landRouteProps;

	public LandRouteProps getConfigProps() {
		return landRouteProps;
	}

	public ResponseWrapper processRoutingRequest(String origin, String destination) {
		ResponseWrapper wrapper = new ResponseWrapper();

		HashMap<String, List<String>> simpleQueue = new HashMap<>();
		simpleQueue = new RoutingClient(landRouteProps).sendGetRequest();
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
		String finalRoute = origin + ", " + commonRoute.substring(1, commonRoute.length() - 1).trim() + ", " + destination;
		List<String> originRoute = new ArrayList<String>(Arrays.asList(finalRoute.split("[\\s,]+")));

		wrapper.setRoute(originRoute);
		return wrapper;
	}

	public HashMap<String, List<String>> persistRecords(HashMap<String, List<String>> simpleQueue) {
		LandRouteModel[] boarders = null;
		// boarders = new RoutingClient(landRouteProps).sendGetRequest();

		for (int i = 0; i < boarders.length; i++) {
			// routeModel.setBorders(boarders[i].getBorders());
			// routeModel.setCca3(boarders[i].getCca3());
			// routeModel.setLandlocked(boarders[i].getLandlocked());
			// routeModel.setLatlng(boarders[i].getLatlng());
			simpleQueue.put(boarders[i].getCca3(), boarders[i].getBorders());
			// repository.save(routeModel);
		}
		LOG.info(timelog + " : LOADED DATA = " + simpleQueue);
		return simpleQueue;
	}

	public HashMap<String, List<String>> persistRec() {
		// LandRouteModel[] boarders = null;
		HashMap<String, List<String>> simpleQueue = null;
		simpleQueue = new RoutingClient(landRouteProps).sendGetRequest();

		LOG.info(timelog + " : LOADED DATA = " + simpleQueue);
		return simpleQueue;

	}

}
