package com.jin.tpdb.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String data = request.getParameter("data");
		System.out.println("##########data = " + data);

		// if (request.getParameter("data").toString() == "tags") {

		List<Tag> tags = DAO.getList(Tag.class);
		JSONArray jsonResponse = new JSONArray();

		for (Tag t : tags) {
			jsonResponse.add(t.getName());
		}
		System.out.println(jsonResponse.toJSONString());
		out.println(jsonResponse.toJSONString());

		// }

	}
}
