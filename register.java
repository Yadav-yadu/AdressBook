package com.phone.book;

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

import javax.servlet.ServletResponse;



public class Register extends HttpServlet {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		String url="jdbc:mysql://localhost:3306/phone_book";
		String un="root";
		String pwd="root123";
		
		public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, un,pwd);
			} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer=resp.getWriter();
		
		String fn = req.getParameter("First_name");
		String ln = req.getParameter("Last_Name");
		String pn = req.getParameter("Phone_Number");
		String em = req.getParameter("Email");
		String pwd = req.getParameter("Password");
		String cpwd = req.getParameter("Confirm_Password");
		
try {
		String query="insert into register(`First_name`,`Last_Name`,`Phone_Number`,`Email`,`password`,`confirm_Password`) values(?,?,?,?,?,?)";
		pstmt=con.prepareStatement(query);
		
		pstmt.setString(1,fn);
		pstmt.setString(2,ln);
		pstmt.setString(3,pn);
		pstmt.setString(4,em);
		pstmt.setString(5,pwd);
		pstmt.setString(6,cpwd);
		pstmt.execute();
				
	writer.println("<h3>Registration successful!</h3>");	    
	
}catch(Exception e) {
	e.printStackTrace();
}
}
}