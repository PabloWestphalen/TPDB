package com.jin.tpdb.controller.contribute;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.News;
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

		if (!request.getParameter("title").isEmpty()
				&& !request.getParameter("content").isEmpty()) {
			News news = new News();
			news.setTitle(request.getParameter("title"));
			news.setContent(request.getParameter("content"));
			User user = DAO.load(User.class, 1);
			news.setUser(user);
			DAO dao = new DAO();
			dao.open();
			dao.save(news);
			dao.close();
			dispatch(request, response);
		}
	}

}