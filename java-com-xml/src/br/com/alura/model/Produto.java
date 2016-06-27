package br.com.alura.model;

public class Produto {

	private String nome;
	private double preco;

	public Produto() {
		super();
	}

	public Produto(String produtoNome, Double preco) {
		this.nome = produtoNome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "nome: " + nome + "  preco " + preco;
	}

}
