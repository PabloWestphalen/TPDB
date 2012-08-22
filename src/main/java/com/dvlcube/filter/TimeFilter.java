package com.dvlcube.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TimeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		long before = System.currentTimeMillis();

		chain.doFilter(request, response);
		long after = System.currentTimeMillis();
		String name = "";
		if (request instanceof HttpServletRequest) {
			name = ((HttpServletRequest) request).getRequestURI();
		}
		System.out.println(name + ": " + (after - before) + "ms");
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}