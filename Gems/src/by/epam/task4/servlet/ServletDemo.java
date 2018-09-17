package by.epam.task4.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.task4.entity.FakeGem;
import by.epam.task4.entity.Gem;
import by.epam.task4.util.factory.GemBuilderFactory;
import by.epam.task4.util.parser.AbstractGemBuilder;


@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger(ServletDemo.class);
	private static final long serialVersionUID = 1L;
       
    public ServletDemo() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String parser = request.getParameter("parser");
			String file = getServletContext().getRealPath(request.getParameter("file"));
			GemBuilderFactory factory = new GemBuilderFactory();
			AbstractGemBuilder builder = factory.createBuilder(parser);
			builder.buildGemsSet(file);
			Set<Gem> gems = builder.getGems();
			LOG.info(gems);
			request.setAttribute("gems", gems);
		
		request.getRequestDispatcher("/jsp/two.jsp").forward(request, response);
	}

}
