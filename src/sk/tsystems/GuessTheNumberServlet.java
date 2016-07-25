package sk.tsystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import sk.tsystems.gamestudio.games.stones.core.Field;

/**
 * Servlet implementation class GuessTheNumberServlet
 */
@WebServlet("/GuessTheNumber")
public class GuessTheNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int maxNumber;
		// session.setAttribute("maxnumber",1);
		int guesses = 0;
		int guess = 0;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.printf("Enter Max Number");
		out.print("<form method=\"get\">");
		out.printf("<input type=\"text\" name = \"maxnumber\"></input>");
		out.printf("<button class=\"button\"  type=\"submit\" >new game</button>");
		out.print("</form>");

		int secretNumber;
		Random random = new Random();
		
		if(session.getAttribute("maxnumber") !=null){
		secretNumber = random.nextInt((int) session.getAttribute("maxnumber")) + 1;
		}
		else{
			secretNumber= random.nextInt(50) +1;
			session.setAttribute("secretnumber",secretNumber);
			
		}
		System.out.println("Insert max number");

		if (session.getAttribute("guesses") != null) {
			guesses = (int) session.getAttribute("guesses");
		}
		try {
			guess = Integer.parseInt(request.getParameter("input"));
			if (guess > 0) {
				guesses++;
			}

			session.setAttribute("guesses", guesses);

		} catch (IllegalArgumentException e) {
			// out.print("<p>Not an number entered. Enter number</p>");
		}

		try {
			maxNumber = Integer.parseInt(request.getParameter("maxnumber"));
			session.setAttribute("maxnumber", maxNumber);
			guesses = 0;
			session.setAttribute("guesses", 0);
			session.setAttribute("secretnumber", secretNumber);
		} catch (IllegalArgumentException e) {
			// System.err.println("Not an number entered. Enter number:");
			// out.print("<p>Not an number entered. Enter number</p>");
		}

		out.printf("<br>enter your guess in range 1 - %d", session.getAttribute("maxnumber"));
		out.print("<form method=\"get\">");
		out.printf("<input type=\"text\" name = \"input\"></input>");
		out.printf("<button class=\"button\"  type=\"submit\" >guess</button>");
		out.print("</form>");

		if (guess == (int) session.getAttribute("secretnumber")) {
			out.println("Your guess is correct. Congratulations! number of turns: " + guesses);
			guesses = 0;
			session.setAttribute("guesses", 0);
		} else if (guess < (int) session.getAttribute("secretnumber")) {
			out.println("Your guess is smaller than the secret number.");
		} else if (guess > (int) session.getAttribute("secretnumber")) {
			out.println("Your guess is greater than the secret number.");
		}
		out.printf("<br>guesses: %d", guesses);
		// out.printf("<br>max: %d",maxNumber);
		// out.printf("<br>maxses: %d",(int)session.getAttribute("maxnumber"));
		// out.printf("<br>random:
		// %d",(int)session.getAttribute("secretnumber"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
