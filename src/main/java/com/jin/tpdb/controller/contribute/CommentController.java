package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jin.Sanitizer;
import com.jin.Utils;
import com.jin.tpdb.entities.AlbumComment;
import com.jin.tpdb.entities.NewsComment;
import com.jin.tpdb.repositories.AlbumRepository;
import com.jin.tpdb.repositories.NewsRepository;

public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private NewsRepository newsRepo;
	@EJB
	private AlbumRepository albumRepo;
	
	private static final int ALBUM = 0;
	private static final int NEWS = 1 ;
	
	/*public void dispatch(Comment c, int option, String requestType, HttpServletResponse response) {
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			PrintWriter out = response.getWriter();
			SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM. yyyy");
			response.setContentType("application/json");
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("id", c.getId());
			jsonResponse.put("imageUrl", (request.getContextPath() + 
					"/images/unregisteredUser.png"));
			jsonResponse.put("comment", Sanitizer.cleanHtml(c.getComment()));
			jsonResponse.put("userName", userName);
			jsonResponse.put("date", sdf.format(c.getDate()));
			System.out.println(jsonResponse);
			out.print(jsonResponse);

		} else {
			try {
				String s = "album/?id=" + albumId + "#cid" + c.getId();
				System.out.println("Redirecting to...." + s);
				response.sendRedirect(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
*/
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Integer albumId = null;
		Integer newsId = null;

		String commentBody = Sanitizer.clean(request
				.getParameter("commentBody"));

		// test session... else ->

		String userName = Sanitizer.clean(request.getParameter("userName"));
		String email = Sanitizer.clean(request.getParameter("email"));
		String userIp = request.getRemoteAddr();
		String ajaxRequest = request.getHeader("X-Requested-With");

		Cookie[] cookies = request.getCookies();

		if (Utils.getCookie(cookies, "userName") == null) {
			Cookie userCookie = new Cookie("userName", userName);
			response.addCookie(userCookie);

		}
		if (Utils.getCookie(cookies, "email") == null) {
			Cookie emailCookie = new Cookie("email", email);
			response.addCookie(emailCookie);
		}

		// end else

		if (request.getParameter("album") != null) {
			albumId = Integer.parseInt(request.getParameter("album"));
			AlbumComment c = new AlbumComment();
			if (!commentBody.isEmpty()) {
				c.setComment(commentBody);
				c.setDate(new Date());
				c.setUserEmail(email);
				c.setUserName(userName);
				c.setUserIP(userIp);
				albumRepo.addComment(c, albumId);
			}
			if (ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest")) {
				PrintWriter out = response.getWriter();
				SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM. yyyy");
				response.setContentType("application/json");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("id", c.getId());
				jsonResponse
						.put("imageUrl",
								(request.getContextPath() + "/images/unregisteredUser.png"));
				jsonResponse
						.put("comment", Sanitizer.cleanHtml(c.getComment()));
				jsonResponse.put("userName", userName);
				jsonResponse.put("date", sdf.format(c.getDate()));
				System.out.println(jsonResponse);
				out.print(jsonResponse);

			} else {
				try {
					String s = "album/?id=" + albumId + "#cid" + c.getId();
					System.out.println("Redirecting to...." + s);
					response.sendRedirect(s);
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}

		} else if (request.getParameter("news") != null) {
			newsId = Integer.parseInt(request.getParameter("news"));
			NewsComment c = new NewsComment();
			if (!commentBody.isEmpty()) {
				c.setComment(commentBody);
				c.setDate(new Date());
				c.setUserName(userName);
				c.setUserEmail(email);
				c.setUserIP(request.getRemoteAddr());
				newsRepo.addComment(c, newsId);
			}
			if (ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest")) {
				PrintWriter out = response.getWriter();
				SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM. yyyy");
				response.setContentType("application/json");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("id", c.getId());
				jsonResponse
						.put("imageUrl",
								(request.getContextPath() + "/images/unregisteredUser.png"));
				jsonResponse
						.put("comment", Sanitizer.cleanHtml(c.getComment()));
				jsonResponse.put("userName", userName);
				jsonResponse.put("date", sdf.format(c.getDate()));
				System.out.println(jsonResponse);
				out.print(jsonResponse);

			} else {

				try {
					String s = "news/?id=" + newsId + "#cid" + c.getId();
					System.out.println("Redirecting to..." + s);
					response.sendRedirect(s);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
}
