package io.mglobe.landroutes.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResponseWrapper {
	List<String> route;
	
	public ResponseWrapper() {
		
	}

	public List<String> getRoute() {
		return route;
	}

	public void setRoute(List<String> route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "ResponseWrapper [route=" + route + "]";
	}
	
	
}
