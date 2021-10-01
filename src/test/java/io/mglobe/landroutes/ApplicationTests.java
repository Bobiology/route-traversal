package io.mglobe.landroutes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mglobe.landroutes.controller.LandRouteController;

/**
 * 
 * @author sakawaelijahbob
 *
 */

//@SpringBootTest
@WebMvcTest(LandRouteController.class)
class ApplicationTests {
	 @Autowired
	    MockMvc mockMvc;
	    @Autowired
	    ObjectMapper mapper;
	
    @Test
    public void login() throws Exception {
        /*
        mockMvc.perform(MockMvcRequestBuilders
                .get("/routing/AFG/TKM")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
*/
    }
    

}
