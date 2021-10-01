package io.mglobe.landroutes.utils;


import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

	@Value("${traversal-route.http-connection-request-timeout}")
	private int CONNECTION_REQUEST_TIMEOUT;
	
	@Value("${traversal-route.http-connection-timeout}")
	private int CONNECTION_CONNECT_TIMEOUT;
	
	@Value("${traversal-route.http-connection-read-timeout}")
	private int CONNECTION_READ_TIMEOUT;
	
	@Bean(name = "RestTemplateClient")
	public RestTemplate customRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	    HostnameVerifier hostnameVerifier = (s, sslSession) -> true;
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setConnectionRequestTimeout(CONNECTION_CONNECT_TIMEOUT);
	    requestFactory.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
	    requestFactory.setConnectTimeout(CONNECTION_CONNECT_TIMEOUT);
	    requestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
	    requestFactory.setHttpClient(httpClient);
	    return new RestTemplate(requestFactory);
	}
	
}
