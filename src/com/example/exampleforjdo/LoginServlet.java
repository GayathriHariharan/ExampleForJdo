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
public class LoginServlet extends HttpServlet {

	PersistenceManager pm= PMF.get().getPersistenceManager();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException , ServletException{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println("inside login servlet code");
		
		
		/*Query q = pm.newQuery(LoginDetails.class);
		q.setFilter("userName == userNameParam");
		//q.setOrdering("height desc");
		q.declareParameters("String userNameParam");
		
		List<LoginDetails> result=(List<LoginDetails>) q.execute(userName);
		if(result.contains(password)){
			out.println("You are successfully logged in");
		}*/
   //     Key key = KeyFactory.createKey(LoginDetails.class.getSimpleName(), userName);
       try{
    	   LoginDetails ld = pm.getObjectById(LoginDetails.class, userName);
    	   if( ( userName.equals(ld.getUserName()) ) && ( password.equals(ld.getPassword()) ) ){
           	out.println("<h3>"+"you are successfully logged in"+"</h3>");
           	request.getRequestDispatcher("options.html").forward(request, response);;
           }else{
        	   out.println("Wrong password");
              	request.getRequestDispatcher("index.html").forward(request, response);;

           }
       }catch(JDOObjectNotFoundException e){
    	   
              	out.println("invalid credentials");
              	request.getRequestDispatcher("index.html").forward(request, response);
              
       }
        
      
		
	}
	
}
