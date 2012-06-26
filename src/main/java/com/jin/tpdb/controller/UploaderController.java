package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;

public class UploaderController extends HttpServlet {
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		boolean isMultipart = FileUpload.isMultipartContent(request);
		if (isMultipart) {
			// Create a new file upload handler
			DiskFileUpload upload = new DiskFileUpload();

			// Set upload parameters
			upload.setSizeMax(50 * 1024 * 1024); // 50Mb
			upload.setRepositoryPath("/");

			// Parse the request
			List items = upload.parseRequest(request);

			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem fitem = (FileItem) it.next();
				if (!fitem.isFormField()) {
					String message = "###" + fitem.getName() + " - "
							+ fitem.getSize() + "bytes";
					System.out.println(message);
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
