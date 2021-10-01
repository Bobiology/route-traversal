package io.mglobe.landroutes.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Configuration
@Service
@PropertySource("classpath:application.yml")
public class LandRouteProps {
	
	@Value("${traversal-route.http-connection-request-timeout}")	
	private String requestTimeout;
	@Value("${traversal-route.http-connection-timeout}")
	private String connectionTimeout;
	@Value("${traversal-route.http-connection-read-timeout}")
	private String readTimeout;
	@Value("${traversal-route.resourceUrl}")
	private String endpoint;
	
	@Value("${spring.security.user.name}")
	private String basicAuthUsername;
	
	@Value("${spring.security.user.password}")
	private String basicAuthPassword;
	
	@Value("${spring.security.user.roles}")
	private String basicAuthRoles;

	public String getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(String requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(String readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getBasicAuthUsername() {
		return basicAuthUsername;
	}

	public void setBasicAuthUsername(String basicAuthUsername) {
		this.basicAuthUsername = basicAuthUsername;
	}

	public String getBasicAuthPassword() {
		return basicAuthPassword;
	}

	public void setBasicAuthPassword(String basicAuthPassword) {
		this.basicAuthPassword = basicAuthPassword;
	}

	public String getBasicAuthRoles() {
		return basicAuthRoles;
	}

	public void setBasicAuthRoles(String basicAuthRoles) {
		this.basicAuthRoles = basicAuthRoles;
	}

	@Override
	public String toString() {
		return "LandRouteProps [requestTimeout=" + requestTimeout + ", connectionTimeout=" + connectionTimeout
				+ ", readTimeout=" + readTimeout + ", endpoint=" + endpoint + ", basicAuthUsername=" + basicAuthUsername
				+ ", basicAuthPassword=" + "XXXXXXXX" + ", basicAuthRoles=" + basicAuthRoles + "]";
	}
	
	

}
