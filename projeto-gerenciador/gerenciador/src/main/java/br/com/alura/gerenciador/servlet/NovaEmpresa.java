package br.com.alura.gerenciador.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

public class NovaEmpresa implements Tarefa {

	private static final long serialVersionUID = 1L;

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		String nomeEmpresa = request.getParameter("nome");
		Empresa empresa = new Empresa(nomeEmpresa);
		new EmpresaDAO().adiciona(empresa);
		
		request.setAttribute("nome", nomeEmpresa);
		
		return "/WEB-INF/paginas/novaEmpresa.jsp";
	}


}
