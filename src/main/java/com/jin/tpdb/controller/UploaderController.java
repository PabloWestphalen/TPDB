package com.jin.tpdb.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploaderController extends HttpServlet {
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {

			File file;
			int maxFileSize = 5 * 1024 * 1024;
			int maxMemSize = 5 * 1024 * 1024;
			String filePath = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads/";

			// Check that we have a file upload request
			isMultipart = ServletFileUpload.isMultipartContent(request);

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);
			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File(System.getenv("OPENSHIFT_TMP_DIR")));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);

			try {
				// Parse the request to get file items.
				List fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();

						System.out.println("####" + fieldName + " - "
								+ fileName + " - " + contentType + " - "
								+ isInMemory + " - " + sizeInBytes);

						// Write the file

						if (fileName.lastIndexOf("/") >= 0) {
							file = new File(filePath
									+ fileName.substring(fileName
											.lastIndexOf("/")));
						} else {
							file = new File(filePath
									+ fileName.substring(fileName
											.lastIndexOf("/") + 1));
						}
						fi.write(file);
						PrintWriter out = response.getWriter();
						/*
						 * out.println("<img src=\"/images/covers/"
						 * 
						 * + fileName +
						 * "\" width=\"85\" height=\"85\" alt=\"Cover\" class=\"cover\" tabindex=\"3\" id=\"coverUploadButton\" />"
						 * );
						 */

						out.println(fileName);
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
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
