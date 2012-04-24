package com.jin.tpdb.controller;

import java.io.*;
import com.jin.tpdb.entities.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.persistence.*;


public class IndexController extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jin");
		EntityManager em = factory.createEntityManager();		
		
		
		String awesome = "secret powerful message =OO";
		
		Macaco monkey = new Macaco();		
		
		Artist artist = em.find(Artist.class, 1);
		
		request.setAttribute("artist", artist);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request.getRequestDispatcher("index.jsp");
		jsp.forward(request, response);
		
	}	
}

/*class Macaco {
	String nome = "Macacolouco";
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}

*/