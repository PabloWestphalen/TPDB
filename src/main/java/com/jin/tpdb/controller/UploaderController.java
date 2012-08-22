package com.jin.tpdb.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

import com.jin.ImageUtils;

@MultipartConfig
public class UploaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked") 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int random = new Random().nextInt();
		Part cover = request.getPart("coverUp");
		String filePath = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads/";
		String extension = getExtension(getFileName(cover));
		String fileName = System.currentTimeMillis() + random + extension;
		cover.write(filePath + fileName);
		File thumbnail = new File(filePath + fileName);
		thumbnail = ImageUtils.createThumbnail(thumbnail, "jpg", false);
		PrintWriter out = response.getWriter();
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("temp_cover_name", thumbnail.getName());
		response.setContentType("application/json");
		out.println(jsonResponse.toString());
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
}