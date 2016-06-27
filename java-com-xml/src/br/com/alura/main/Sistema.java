package br.com.alura.main;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.alura.model.Produto;

public class Sistema {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// DocumentBuilderFactory factory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder builder = factory.newDocumentBuilder();
		// Document document = builder.parse("src/venda.xml");
		//
		// NodeList produtos = document.getElementsByTagName("produto");
		//
		// for(int i =0; i < produtos.getLength(); i++) {
		// Element produto = (Element) produtos.item(i);
		// String nome =
		// produto.getElementsByTagName("nome").item(0).getTextContent();
		// String preco =
		// produto.getElementsByTagName("preco").item(0).getTextContent();
		//
		// System.out.println("-----------");
		// System.out.println("Nome do produto: "+ nome);
		// System.out.println("Preco do produto: "+ preco);
		// System.out.println("-----------");
		// }

		// Element moeda = document.getDocumentElement();
		// System.out.println(moeda.getAttribute("moeda"));
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		fabrica.setValidating(true);
		fabrica.setNamespaceAware(true);
		fabrica.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		DocumentBuilder builder = fabrica.newDocumentBuilder();
		Document document = builder.parse("src/venda.xml");
		
		String exp = "/venda/produtos/produto[nome='arroz']";
		XPath path = XPathFactory.newInstance().newXPath();
		XPathExpression expression = path.compile(exp);
		NodeList produtos = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
		
//		NodeList produtos = document.getElementsByTagName("produto");
		// NodeList precos = document.getElementsByTagName("preco");
		for (int i = 0; i < produtos.getLength(); i++) {
			Element produto = (Element) produtos.item(i);
			String produtoNome = produto.getElementsByTagName("nome").item(0).getTextContent();
			String preco = produto.getElementsByTagName("preco").item(0).getTextContent();
			Produto p = new Produto(produtoNome, Double.valueOf(preco));
			System.out.println(p);
		}

		// Element fdp = (Element) formaDePagamento.item(0);
	}

}
