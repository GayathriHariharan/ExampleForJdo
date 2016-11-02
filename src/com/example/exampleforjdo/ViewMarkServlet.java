package com.example.exampleforjdo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class ViewMarkServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException , ServletException{
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		Query q = pm.newQuery(Student.class);
		//q.setOrdering("name asc");

		try {
		  @SuppressWarnings("unchecked")
		List<Student> results = (List<Student>) q.execute();
		  if (!results.isEmpty()) {
		    for (Student p : results) {
		      // Process result p
		    	out.println("--------------------"+"<br>");
		    	out.println("Name : "+p.getName()+"<br>");
		    	out.println("Mark : "+p.getMark()+"<br>");
		    	out.println("--------------------"+"<br>");

		    }
		  } else {
		    // Handle "no results" case
			  out.println("No entry found.. Please add any entry..");
			  request.getRequestDispatcher("options.html").forward(request, response);
		  }
		} finally {
		  q.closeAll();
		 out.println(" <br><form action='options.html'>"+
		  "<input type='submit' value='Back'>"+
		 " </form>");
		}
		
	}
}
