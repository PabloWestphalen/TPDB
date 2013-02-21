package com.jin.tpdb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.util.JsonMaker;

public class ExternalAPIs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String site = request.getParameter("site");
		String url = new String();
		if(site != null && site.equals("discogs_search")) {
			String artistName = request.getParameter("artist");
			String albumName = request.getParameter("album");
			url = "http://api.discogs.com/database/search?type=master&artist=" + artistName + "&title=" + albumName;
		}
		if(site != null && site.equals("discogs_masterdetail")) {
			url = "http://api.discogs.com/" + request.getParameter("path");
		}
		response.setContentType("application/json");
		out.print(JsonMaker.getJson(url));
	}
}