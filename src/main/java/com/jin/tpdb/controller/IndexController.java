package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.News;
import com.jin.tpdb.repositories.AlbumRepository;
import com.jin.tpdb.repositories.NewsRepository;

public class IndexController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  @EJB
  private NewsRepository newsRepo;
  
  @EJB
  private AlbumRepository albumRepo;

  public void processRequest(HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {
    Long before, after;
    before = System.currentTimeMillis();
    List<News> newsList = newsRepo.getLatestNews();

    List<Album> albumsList = albumRepo.getAlbums(4);
    List<Album> featuredAlbumsList = albumRepo.getFeaturedAlbums();
    after = System.currentTimeMillis();
    System.out.println("Time to get everything from the database: " + (after - before) + "ms");
    request.setAttribute("newsList", newsList);
    request.setAttribute("albums", albumsList);
    request.setAttribute("featuredAlbums", featuredAlbumsList);
    RequestDispatcher jsp = request.getRequestDispatcher("index.jsp");
    System.out.println("######## rodei no cloud-ide.com!!!!!!!#########");
    jsp.forward(request, response);

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
