package com.simplilearn.filters;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/filterServlet") // Apply this filter to request for /filterServlet URL pattern
public class LoggingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Request received at "+ new java.util.Date());
		
		chain.doFilter(request, response);
		
		System.out.println("Response processed at "+ new java.util.Date());
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
