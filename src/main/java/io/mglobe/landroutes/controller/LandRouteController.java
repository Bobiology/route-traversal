package io.mglobe.landroutes.controller;

import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.mglobe.landroutes.model.ResponseWrapper;
import io.mglobe.landroutes.service.LandRouteService;

@RestController
@RequestMapping(value = "/routing")
public class LandRouteController {
	@Autowired
	LandRouteService routingService;

	public static final Logger LOG = LogManager.getLogger(LandRouteController.class);
	String timelog = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date());

	@RequestMapping(path = "/{origin}/{destination}", consumes = {
			"application/json" }, method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseWrapper> getBoarders(@PathVariable("origin") String origin,
			@PathVariable("destination") String destination) {
		LOG.info(timelog + " : RECEIVED PARAMS {}, {}", origin, destination);

		ResponseWrapper respo = routingService.processRoutingRequest(origin, destination);

		if (origin == null || origin.isEmpty()) {
			return ResponseEntity.status(412).body(null);
		} else if (destination == null || destination.isEmpty()) {
			return ResponseEntity.status(412).body(null);
		} else if (respo.getRoute().isEmpty() || respo.getRoute() == null || respo.getRoute().toArray().length < 3) {
			LOG.info(timelog + " :NOT FOUND");
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.status(200).body(respo);
		}
	}

}