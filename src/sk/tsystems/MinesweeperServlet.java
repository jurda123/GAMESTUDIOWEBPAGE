package sk.tsystems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.minesweeper.core.GameState;
import sk.tsystems.gamestudio.games.minesweeper.core.Clue;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;
import sk.tsystems.gamestudio.games.minesweeper.core.Mine;
import sk.tsystems.gamestudio.games.minesweeper.core.Tile;

/**
 * Servlet implementation class MinesweeperServlet
 */
@WebServlet("/Minesweeper")
public class MinesweeperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='/WebProject/Minesweeper.css' type='text/css'>");
		out.println("</head>");
		out.println("<body>");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		out.println("<script src='/WebProject/jquery.min.js'></script>");
		Field field = (Field) session.getAttribute("mfield");
		if (field == null) {
			field = new Field(10, 10, 9);
			session.setAttribute("mfield", field);
		}

		try {
			int i = Integer.parseInt(request.getParameter("i"));
			int j = Integer.parseInt(request.getParameter("j"));
			field.openTile(i, j);

			if (field.getState() == GameState.SOLVED) {
				out.println("<h1>you win</h1>");

			}
			if (field.getState() == GameState.FAILED) {
				out.println("<h1>Game over!</h1>");

			}
		} catch (Exception e) {
		}

		try {
			int i = Integer.parseInt(request.getParameter("a"));
			int j = Integer.parseInt(request.getParameter("b"));
			int m = Integer.parseInt(request.getParameter("m"));
			if (m == 1) {
				field.markTile(i, j);
			}
		} catch (Exception e) {
		}
		
		try {
			int newx = Integer.parseInt(request.getParameter("newx"));
			int newy = Integer.parseInt(request.getParameter("newy"));
			int newm = Integer.parseInt(request.getParameter("newm"));
			field= new Field(newx,newy,newm);
			session.setAttribute("mfield", field);
		} catch (Exception e) {
		}

		try {
			int i = Integer.parseInt(request.getParameter("newgame"));
			if (i == 1) {
				Field tempfield = (Field) session.getAttribute("mfield");
				field = new Field(tempfield.getRowCount(), tempfield.getColumnCount(), tempfield.getMineCount());
				session.setAttribute("mfield", field);
			}
		} catch (Exception e) {
		}

		out.print("<table border='1'>");
		out.println("<tr>");
		//out.print("<td>");
	//	out.print(" ");
//		for (int i = 0; i < field.getColumnCount(); i++) {
//			out.println("<td>");
//			out.print(i);
//		}
//		out.println();
		Tile tile;
		// <a href='?value=%d'>%2d</a>
		for (int i = 0; i < field.getRowCount(); i++) {
			out.println("<tr>");
			//out.println("<td>");
			//int znak = i + 65;
		//	char a = ((char) znak);
		//	out.print(a);
			for (int j = 0; j < field.getColumnCount(); j++) {
				out.printf("<td id='td%d'>", field.getColumnCount() * i + j);
				tile = field.getTile(i, j);
				if (tile.getState() == Tile.State.CLOSED) {
					out.print("<form method='get'>");
					out.printf("<textarea name='i' hidden='true'>%d</textarea>", i);
					out.printf("<textarea name='j' hidden = true>%d</textarea>", j);
					// out.printf("<textarea id
					// ='t%d'name='m'>%d</textarea>",field.getColumnCount()*i+j,0);
					out.printf("<button  class='openbutton'  type='submit' >open</button>",
							field.getColumnCount() * i + j);
					out.print("</form>");
					out.print("<form method='get'>");
					out.printf("<textarea name='a' hidden='true'>%d</textarea>", i);
					out.printf("<textarea name='b' hidden = true>%d</textarea>", j);
					out.printf("<textarea name='m' hidden='true'>%d</textarea>", 1);
					out.print("<button class='markbutton'  type='submit' >mark</button>");
					out.print("</form>");
					out.print("<script>");
					out.printf("$('#td%d').css('background', 'lightsteelblue');", field.getColumnCount() * i + j);
					out.print("</script>");

					// out.print("<script>");
					// out.printf("$(\"button#b%d\").mousedown(function(ev){",field.getColumnCount()*i+j);
					// out.print(" if(ev.which == 3)");
					// out.print(" {");
					// out.printf("$(\"textarea#t%d\").val('1')",field.getColumnCount()*i+j);
					// out.print(" }");
					// out.print("});");
					// out.print("</script>");

				} else if (tile.getState() == Tile.State.MARKED) {
					out.print("M");
					out.print("<form method='get'>");
					out.printf("<textarea name='a' hidden='true'>%d</textarea>", i);
					out.printf("<textarea name='b' hidden = true>%d</textarea>", j);
					out.printf("<textarea name='m' hidden='true'>%d</textarea>", 1);
					out.print("<button class='markbutton'  type='submit' >unmark</button>");
					out.print("</form>");
					out.print("<script>");
					out.printf("$('#td%d').css('background', 'palegoldenrod');", field.getColumnCount() * i + j);
					out.print("</script>");

				} else if (tile.getState() == Tile.State.OPEN) {
					if (tile instanceof Mine) {
						out.print("X");
						out.print("<script>");
						out.printf("$('#td%d').css('background', 'red');", field.getColumnCount() * i + j);
						out.print("</script>");
					} else if (tile instanceof Clue) {
						out.print(((Clue) tile).getValue());
						out.print("<script>");
						out.printf("$('#td%d').css('background', 'white');", field.getColumnCount() * i + j);
						out.print("</script>");
					}
				}
				out.println();
			}
		}
		out.println("</table>");
		out.print("<br><br>");
		out.print("<form method='get'>");
		out.printf("<textarea name='newgame' hidden = true>1</textarea>");
		out.printf("<button class='button'  type='submit' >NEW GAME</button>");
		out.print("</form>");
		out.println("</body>");
		out.println("</html>");

		
		out.print("<br><form method='get'>");
		out.printf("<input name = \"newx\" width=\"10\" placeholder=\"columns\"></input>");
		out.printf("<input name = \"newy\" placeholder=\"rows\"></input>");
		out.printf("<input name = \"newm\" placeholder=\"mines\"></input>");
		out.printf("<button class\"button\"  type=\"submit\" >NEW FIELD</button>");
		out.print("</form>");
//		out.println("<div w3-include-html=\"/WebContent/jsp/comment.jsp\"></div>");
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
