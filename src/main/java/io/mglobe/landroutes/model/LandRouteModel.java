package io.mglobe.landroutes.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LandRouteModel {
	private String cca3;
	private List<String> borders;
	private String landlocked;
	private List<Double> latlng;

	public LandRouteModel() {

	}

	public String getCca3() {
		return cca3;
	}

	public void setCca3(String cca3) {
		this.cca3 = cca3;
	}

	public List<String> getBorders() {
		return borders;
	}

	public void setBorders(List<String> borders) {
		this.borders = borders;
	}

	public String getLandlocked() {
		return landlocked;
	}

	public void setLandlocked(String landlocked) {
		this.landlocked = landlocked;
	}

	public List<Double> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Double> latlng) {
		this.latlng = latlng;
	}

	public HashMap<String, List<String>> getBoardermap() {
		return new HashMap<String, List<String>>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			{
				put(getCca3(), getBorders());
			}
		};
	}

	@Override
	public String toString() {
		return "LandRouteModel [cca3=" + cca3 + ", borders=" + borders + ", landlocked=" + landlocked + ", latlng="
				+ latlng + "]";
	}

}