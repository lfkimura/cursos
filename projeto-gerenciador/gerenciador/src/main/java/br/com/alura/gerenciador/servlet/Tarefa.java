package br.com.alura.gerenciador.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Tarefa {
	String executa(HttpServletRequest request, HttpServletResponse response);
}
