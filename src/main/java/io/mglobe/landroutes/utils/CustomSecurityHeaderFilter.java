package io.mglobe.landroutes.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

public class CustomSecurityHeaderFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Basic ")) {
			response.sendError(400, "Authorization Header is missing.");
		}
		else {
			chain.doFilter(request, response);
		}
       
       
		
	}
}