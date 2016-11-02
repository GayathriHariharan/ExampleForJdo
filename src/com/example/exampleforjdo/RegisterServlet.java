package com.example.exampleforjdo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		 
		PersistenceManager pm= PMF.get().getPersistenceManager();
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		LoginDetails ld=new LoginDetails();
		
		ld.setUserName(userName);
		ld.setPassword(password);
		
		try{
			 ld = pm.getObjectById(LoginDetails.class, userName);
		 	   out.println("Username already exist");
				request.getRequestDispatcher("register.html").forward(request, response);

		}catch(JDOObjectNotFoundException e){
			
			pm.makePersistent(ld);
			out.println("registered successfully");
			request.getRequestDispatcher("index.html").forward(request, response);
			
		}finally{
			pm.close();
		}
		}
	
}
