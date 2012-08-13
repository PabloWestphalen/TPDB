package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.AlbumComment;
import com.jin.tpdb.entities.NewsComment;
import com.jin.tpdb.repositories.AlbumRepository;
import com.jin.tpdb.repositories.NewsRepository;

public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB private NewsRepository newsRepo;
	@EJB private AlbumRepository albumRepo;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		Integer albumId = null;
		Integer newsId = null;

		String commentBody = Sanitizer.clean(request
				.getParameter("commentBody"));

		// test session... else ->

		String userName = Sanitizer.clean(request.getParameter("userName"));
		String email = Sanitizer.clean(request.getParameter("email"));
		String userIp = request.getRemoteAddr();

		// end else

		if (request.getParameter("albumId") != null) {
			albumId = Integer.parseInt(request.getParameter("albumId"));
			AlbumComment c = new AlbumComment();
			if (!commentBody.isEmpty()) {
				c.setComment(commentBody);
				c.setDate(new Date());
				c.setUserEmail(email);
				c.setUserName(userName);
				c.setUserIP(userIp);
				albumRepo.addComment(c, albumId);
			}
			try {
				response.sendRedirect("album/?id=" + albumId + "#cid"
						+ c.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("newsId") != null) {
			newsId = Integer.parseInt(request.getParameter("newsId"));
			NewsComment c = new NewsComment();
			if (!commentBody.isEmpty()) {
				c.setComment(commentBody);
				c.setDate(new Date());
				c.setUserName(userName);
				c.setUserEmail(email);
				c.setUserIP(request.getRemoteAddr());
				newsRepo.addComment(c, newsId);

			}
			try {
				response.sendRedirect("news/?id=" + newsId + "#cid" + c.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
