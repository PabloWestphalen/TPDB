package com.jin.tpdb.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.jin.tpdb.entities.Tag;
import com.jin.tpdb.persistence.DAO;

public class AjaxDataController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String data = request.getParameter("data");
		out.println("#1#" + data);
		out.println("#2#" + request.getParameter("data"));
		Map mapRequest = request.getParameterMap();
		Map.Entry entryRequest;
		Iterator iteratorRequest = mapRequest.entrySet().iterator();

		String s = "";
		String key;

		while (iteratorRequest.hasNext()) {
			entryRequest = (Map.Entry) iteratorRequest.next();

			key = (String) entryRequest.getKey();

			for (int i = 0; i < request.getParameterValues(key).length; i++)
				s += key + ": " + request.getParameterValues(key)[i] + "<br>";
		}

		out.println("#3#" + s);

		if (data == "tags") {

			List<Tag> tags = DAO.getList(Tag.class);
			JSONArray jsonResponse = new JSONArray();

			for (Tag t : tags) {
				jsonResponse.add(t.getName());
			}

			out.println(jsonResponse.toJSONString());

		}

	}
}
