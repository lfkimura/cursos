package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/fazTudo")
public class FazTudo extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String tarefa = req.getParameter("tarefa");
			String nomeDaClasse = "br.com.alura.gerenciador.servlet." + tarefa;
			Class type = Class.forName(nomeDaClasse);
			Tarefa instancia = (Tarefa) type.newInstance();

			String executa = instancia.executa(req, resp);
			RequestDispatcher dispatcher = req.getRequestDispatcher(executa);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
