package br.com.alura.gerenciador.servlet;

import java.util.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

public class BuscaEmpresa implements Tarefa {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Instanciando uma Servlet do tipo BuscaEmpresa " + this);
		String filtro = request.getParameter("filtro");

		Collection<Empresa> empresas = new EmpresaDAO().buscaPorSimilaridade(filtro);

		request.setAttribute("empresas", empresas);

		return "/WEB-INF/paginas/novaEmpresa.jsp";

	}
}
