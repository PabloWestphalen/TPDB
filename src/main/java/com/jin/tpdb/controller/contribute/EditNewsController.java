package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Tag;
import com.jin.tpdb.entities.User;
import com.jin.tpdb.persistence.DAO;

public class EditNewsController extends HttpServlet {

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request
				.getRequestDispatcher("/contribute/news.jsp");
		jsp.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String title = Sanitizer.clean(request.getParameter("title"));
		String content = Sanitizer.clean(request.getParameter("content"));

		if (!title.isEmpty() && !content.isEmpty()) {
			DAO dao = new DAO();
			dao.open();
			News news = new News();
			User user = DAO.load(User.class, 1);
			news.setTitle(title);
			news.setContent(content);
			news.setUser(user);

			ArrayList<Tag> tags = new ArrayList<Tag>();

			Tag tag = new Tag();
			tag.setName(request.getParameter("tags"));
			dao.save(tag);

			// tags.add(tag);

			if (tags != null) {
				news.getTags().add(tag);
			}

			dao.save(news);
			dao.close();
			dispatch(request, response);
		}
	}

}