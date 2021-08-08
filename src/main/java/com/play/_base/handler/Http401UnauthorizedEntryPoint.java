package com.play._base.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException
	) throws IOException, ServletException {
		
		boolean somePath = request.getServletPath().equals("/somePath");
		
		if (somePath) {
			response.sendError(403, "Access Denied");
		} else {
			response.sendRedirect("/login");
		}
	}

	
}
