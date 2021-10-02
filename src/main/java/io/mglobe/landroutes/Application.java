package io.mglobe.landroutes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"io.mglobe.landroutes"})
public class Application {
	public static final Logger LOG = LogManager.getLogger(Application.class);
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		LOG.info("===================================== STARTING ====================================");
		LOG.info("================== LAND TRAVERSAL ROUTES SERVICE ==================");
		LOG.info("===================================================================================");

	}

}
