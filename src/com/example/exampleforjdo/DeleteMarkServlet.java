package com.example.exampleforjdo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteMarkServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException , ServletException{
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		PrintWriter out=response.getWriter();

	String name = request.getParameter("name");
	
	 try{
  	   Student s = pm.getObjectById(Student.class, name);
  	   pm.deletePersistent(s);
  	   out.println("Deleted successfully");
      	request.getRequestDispatcher("options.html").forward(request, response);
  	   
	 }catch(JDOObjectNotFoundException e){
  	   
       	out.println("Name doesn't exist..Please enter any other name..");
       	request.getRequestDispatcher("delete.html").forward(request, response);
       
}
	}
}
