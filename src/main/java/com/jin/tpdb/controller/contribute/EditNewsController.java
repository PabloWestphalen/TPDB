package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Tag;
import com.jin.tpdb.entities.User;
import com.jin.tpdb.persistence.DAO;

public class EditNewsController extends HttpServlet {

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		String tags = Sanitizer.clean(request.getParameter("tags"));
		String[] tagsArray = tags.replace(", ", "%SPLIT")
				.replace(",", "%SPLIT").split("%SPLIT");

		if (!title.isEmpty() && !content.isEmpty()) {
			DAO dao = new DAO();
			dao.open();
			News news = new News();
			User user = DAO.load(User.class, 1);
			news.setTitle(title);
			news.setContent(content);
			news.setUser(user);

			ArrayList<Tag> tagsCollection = new ArrayList<Tag>();

			for (String s : tagsArray) {
				Tag x = (Tag) DAO.load(Tag.class, Restrictions.like("name", s));
				if (x != null) {
					tagsCollection.add(x);
				} else {
					Tag t = new Tag();
					t.setName(s);
					dao.save(t);
					tagsCollection.add(t);
				}
			}

			news.setTags(tagsCollection);

			/*
			 * if (news.getTags() != null) { Tag tag2 = new Tag();
			 * tag2.setName("dynamic tag"); dao.save(tag2);
			 * news.getTags().add(tag2); DAO.load(Tag.class,
			 * Restrictions.like("name", tt));
			 * 
			 * }
			 */

			dao.save(news);
			dao.close();
			dispatch(request, response);
		}
	}
}