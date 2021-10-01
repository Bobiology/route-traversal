package io.mglobe.landroutes.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	//@Autowired
	//RouteRepository repository;
	@Autowired
	LandRouteProps landRouteProps;

	public LandRouteProps getConfigProps() {
		return landRouteProps;
	}
	
	public ResponseWrapper processRoutingRequest(String origin, String destination){
		LandRouteModel[] boarders = null;

		ResponseWrapper wrapper = new ResponseWrapper();
		
		boarders = new RoutingClient(landRouteProps).sendGetRequest();
		//List<String> resp = sanitizeResponse(boarders,origin,destination);
		List<String> resp = sanitizeResponse(boarders, origin,destination);
		
		wrapper.setRoute(resp);
		return wrapper;
	}
	public List<String> sanitizeResponse(LandRouteModel[] locationData, String origin, String destination){
		Set<String> visited = new HashSet<>();
		List<String> output = new ArrayList<>();
		//RouteModel[] locationData = repository.findByCca3(origin);
		
		// O(N * M)
		// N = size array
		// M size boarder
		int n = locationData.length;
		for (int i = 0; i < n; i++) {
			if (locationData[i].getBoardermap().get(origin) != null) {
				output.add(origin);
				locationData[i].getBoardermap().get(origin).forEach(c -> {
					visited.add(c);
				});
			}
			if (locationData[i].getBoardermap().get(destination) != null) {
				locationData[i].getBoardermap().get(destination).forEach(c -> {
					if (visited.contains(c)) {
						output.add(c);
					}
				});
			}

		}
		if (output.size() >= 2) {
			output.add(destination);
		}
		return output;
	}
	
	public RouteModel persistRecords() {
		LandRouteModel[] boarders = null;
		RouteModel routeModel = new RouteModel();
		boarders = new RoutingClient(landRouteProps).sendGetRequest();
		
		for(int i=0; i<boarders.length;i++) {
			routeModel.setBorders(boarders[i].getBorders());
			routeModel.setCca3(boarders[i].getCca3());
			routeModel.setLandlocked(boarders[i].getLandlocked());
			routeModel.setLatlng(boarders[i].getLatlng());
			//repository.save(routeModel);
		}
		LOG.info(timelog+" : LOADED DATA = "+routeModel);
		return routeModel;
		
	}
}
