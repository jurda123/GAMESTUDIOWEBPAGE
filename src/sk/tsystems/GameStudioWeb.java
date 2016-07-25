package sk.tsystems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sk.tsystems.gamestudio.services.CommentService;
import sk.tsystems.gamestudio.services.jpa.CommentHibernate;

/**
 * Servlet implementation class GameStudioWeb
 */
@WebServlet("/GameStudio")
public class GameStudioWeb extends HttpServlet {
	String gameName = null;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String comment = request.getParameter("comment");
		if ("minesweeper".equals(action)) {
			request.setAttribute("action", "minesweeper");

			request.getRequestDispatcher("/WEB-INF/jsp/minesweeper.jsp").include(request, response);
			gameName = "Minesweeper";
			System.out.print(gameName + comment);
			if (!comment.equals("")){
				CommentService c = new CommentHibernate(comment, gameName);
				c.addComment();
				System.out.println("kappa");
				}
			// response.sendRedirect("/WebProject/WEB-INF/jsp/minesweeper.jsp");//.forward(request,
			// response);
			// forwardToList(request, response);
		} else if ("stones".equals(action)) {
			request.setAttribute("action", "stones");
			request.getRequestDispatcher("/WEB-INF/jsp/stones.jsp").forward(request, response);
			gameName = "Stones";
			// request.
			// response.sendRedirect("/WebProject/Stones");
			// forwardToList(request, response);
		} else if ("guessthenumber".equals(action)) {
			request.setAttribute("action", "guessthenumber");
			response.sendRedirect("/WebProject/GuessTheNumber");
			gameName = "GuessTheNumber";
			// forwardToList(request, response);
		} else {
			forwardToList(request, response);
		}
		
		
	}

	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setAttribute("students", students.values());
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

//	 protected void doGet(HttpServletRequest request, HttpServletResponse
//	 response) throws ServletException, IOException {
//	 PrintWriter out = response.getWriter();
//	 String comment = request.getParameter("comment");
//			if (!comment.equals("")){
//			CommentService c = new CommentHibernate(comment, gameName);
//			c.addComment();
//			System.out.println("kappa");
//			}
//	 }
//	
//	 /**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 response)
//	 */
//	 protected void doPost(HttpServletRequest request, HttpServletResponse
//	 response) throws ServletException, IOException {
//	 // TODO Auto-generated method stub
//	 doGet(request, response);
//	 }

}
