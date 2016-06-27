package br.com.alura.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ConversorParaHtml {
	public static void main(String[] args)
			throws TransformerFactoryConfigurationError, FileNotFoundException, TransformerException {
		InputStream xsl = new FileInputStream("src/xslParaHtml.xsl");
		StreamSource st = new StreamSource(xsl);

		InputStream dados = new FileInputStream("src/venda.xml");
		StreamSource xmlsourcer = new StreamSource(dados);
		Transformer transformer = TransformerFactory.newInstance().newTransformer(st);

		StreamResult saida = new StreamResult("src/vendas.html");
		transformer.transform(xmlsourcer, saida);
		System.out.println("Conversao concluida");

	}
}
