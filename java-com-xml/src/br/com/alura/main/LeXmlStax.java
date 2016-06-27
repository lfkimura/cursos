package br.com.alura.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import br.com.alura.model.Produto;

public class LeXmlStax {
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		InputStream is = new FileInputStream("src/vendas.xml");
		XMLInputFactory reader = XMLInputFactory.newInstance();
		XMLEventReader eventos = reader.createXMLEventReader(is);

		List<Produto> produtos = new ArrayList<Produto>();

		while (eventos.hasNext()) {
			XMLEvent evento = eventos.nextEvent();

			Produto produto = null;
			if (evento.isStartDocument() && evento.asStartElement().getName().getLocalPart().equals("produto"))
				produto = criaUmProduto(eventos);
			produtos.add(produto);
		}

	}

	private static Produto criaUmProduto(XMLEventReader eventos) throws XMLStreamException {
		Produto produto = new Produto();
		while (eventos.hasNext()) {
			XMLEvent evento = eventos.nextEvent();
			if (evento.isStartDocument() && evento.asStartElement().getName().getLocalPart().equals("nome")) {
				evento = eventos.nextEvent();
				String conteudo = evento.asCharacters().getData();
				produto.setNome(conteudo);
			} else if (evento.isStartDocument() && evento.asStartElement().getName().getLocalPart().equals("preco")) {
				evento = eventos.nextEvent();
				String conteudo = evento.asCharacters().getData();
				produto.setPreco(Double.parseDouble(conteudo));
			} else if (evento.isEndElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {
				break;
			}
		}
		return produto;
	}

}
