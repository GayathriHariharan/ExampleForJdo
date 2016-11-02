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
public class AddMarkServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException , ServletException{
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		PrintWriter out=response.getWriter();

	String name = request.getParameter("name");
	String mark = request.getParameter("mark");
	Student s=new Student();
	s.setMark(mark);
	s.setName(name);
	
	try{
 	   s = pm.getObjectById(Student.class, name);
 	   out.println("name already exist");
		request.getRequestDispatcher("add.html").forward(request, response);


	}catch(JDOObjectNotFoundException e){
		
		pm.makePersistent(s);
		out.println(name+"'s "+" mark has been added successfully");
		request.getRequestDispatcher("options.html").forward(request, response);
		
	}finally{
		pm.close();
	}
	
	}
}
