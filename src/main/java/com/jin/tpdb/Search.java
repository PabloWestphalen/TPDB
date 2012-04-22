package com.jin.tpdb;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Search extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String q = request.getParameter("Search");
		
		out.println("You searched for: " + q);
		
	}
	
}
		
		
		