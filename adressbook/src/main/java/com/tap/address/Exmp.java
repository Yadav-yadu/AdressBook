package com.tap.address;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class Exmp extends HttpServlet {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	String url = "jdbc:mysql://localhost:3306/addressbook";
	String un = "root";
	String pwd = "yadav";
	
	
	 
	@Override
	public void init() throws ServletException {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		     con = DriverManager.getConnection(url, un, pwd); 
		  
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		 String usn = req.getParameter("username");
		 String pwd = req.getParameter("password");
		 
		 try {

			 String query="select * from book where email=? and password=?";
			 
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1, usn);
			  pstmt.setString(2, pwd);
			    res = pstmt.executeQuery();
			    
		    if (res.next()) {
				writer.println("<h3>welcome "+res.getString(2) + "!</h3>");
			} else {
				writer.println("<h3>invalid user or password !! try again</h3>");
			}
			 
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	 
}
