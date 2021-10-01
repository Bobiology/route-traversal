package io.mglobe.landroutes.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Embeddable
@Table(name = "routes")
public class RouteModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8429839982468798221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "cca3")
	private String cca3;

	@Column(name = "borders")
	private List<String> borders;

	@Column(name = "landlocked")
	private String landlocked;
	
	@Column(name = "latitutelongititute")
	private List<Double> latlng;
	
	
	public RouteModel() {

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "RouteModel [id=" + id + ", cca3=" + cca3 + ", borders=" + borders + ", landlocked=" + landlocked
				+ ", latlng=" + latlng + "]";
	}
	
	
}
