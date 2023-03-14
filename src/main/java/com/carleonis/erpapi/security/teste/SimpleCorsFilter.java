package com.carleonis.erpapi.security.teste;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sun.org.apache.xalan.internal.xsltc.dom.Filter;

//@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
	
	public void init(final FilterConfig filterConfig) throws ServletException {
	
	}

	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
	    final HttpServletRequest request = (HttpServletRequest) servletRequest;
	
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with, authorization, cache-control");
	
	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	      response.setStatus(HttpServletResponse.SC_OK);
	    } else {
	    	filterChain.doFilter(servletRequest, servletResponse);
	    }
	}

     public void destroy() {
	 }

	@Override
	public boolean test(int node) {
		// TODO Auto-generated method stub
		return false;
	}
}