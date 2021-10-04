package io.mglobe.landroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.mglobe.landroutes.controller.LandRouteController;
import io.mglobe.landroutes.model.ResponseWrapper;

/**
 * 
 * @author sakawaelijahbob
 *
 */

//Executing unit tests
@WebMvcTest(LandRouteController.class)
class ApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	
	@InjectMocks
	ResponseWrapper responseWrapper;
	
	@Mock
	LandRouteController landRouteController;
	
	//login test case
	@Test
	public void login() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/routing/AFG/TKM")
				.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	//First test case
	@Test
    public void getFirstTestRoute() throws Exception {
		
		List<String> routes = new ArrayList<>();
		routes.add("AFG");
		routes.add("IRN");
		routes.add("UZB");
		routes.add("TKM");
	
		responseWrapper.setRoute(routes);
		when(landRouteController.getBoarders("AFG", "TKM")).thenReturn(ResponseEntity.status(200).body(responseWrapper));
		
		assertEquals(4, responseWrapper.getRoute().size());
    }
	//Second test case
	@Test
    public void getSecondTestRoute() throws Exception {
		
		List<String> routes = new ArrayList<>();
		routes.add("CZE");
		routes.add("AUT");
		routes.add("ITA");
	
		responseWrapper.setRoute(routes);
		when(landRouteController.getBoarders("CZE", "ITA")).thenReturn(ResponseEntity.status(200).body(responseWrapper));
		
		assertEquals(3, responseWrapper.getRoute().size());
    }

}
