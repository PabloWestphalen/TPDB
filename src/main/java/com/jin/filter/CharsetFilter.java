package com.jin.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CharsetFilter implements Filter {


    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}