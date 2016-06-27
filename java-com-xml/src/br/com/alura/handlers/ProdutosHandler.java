package br.com.alura.handlers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.alura.model.Produto;

public class ProdutosHandler extends DefaultHandler {

	private List<Produto> produtos = new ArrayList<Produto>();
	private StringBuilder conteudo = new StringBuilder();
	private Produto produto;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("produto")) {
			produto = new Produto();
		}
		conteudo = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("produto")) {
			produtos.add(produto);
		} else if (qName.equals("nome")) {
			String nome = conteudo.toString();
			produto.setNome(nome);
		} else if (qName.equals("preco")) {
			Double preco = Double.valueOf(conteudo.toString());
			produto.setPreco(preco);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		conteudo.append(new String(ch, start, length));
	}

}
