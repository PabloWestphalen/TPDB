package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Tag;
import com.jin.tpdb.entities.User;
import com.jin.tpdb.repositories.NewsRepository;

public class EditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private NewsRepository newsRepo;

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
			User user = newsRepo.getUser();
			Set<Tag> tagsCollection = new HashSet<Tag>();

			for (String tagName : tagsArray) {
				Tag x = (Tag) newsRepo.getTagByName(tagName);
				if (x != null) {
					tagsCollection.add(x);
				} else {
					Tag t = new Tag();
					t.setName(tagName);
					newsRepo.addTag(t);
					tagsCollection.add(t);
				}
			}
			News news = new News(title, content, user, tagsCollection);

			newsRepo.save(news);
			dispatch(request, response);
		}
	}
}