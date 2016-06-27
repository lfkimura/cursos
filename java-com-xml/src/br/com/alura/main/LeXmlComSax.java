package br.com.alura.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import br.com.alura.handlers.ProdutosHandler;

public class LeXmlComSax {
	public static void main(String[] args) throws SAXException, IOException {
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		ProdutosHandler handler= new ProdutosHandler();
		reader.setContentHandler(handler);
		InputStream inps =  new FileInputStream("src/venda.xml");
		InputSource is = new InputSource(inps);
		reader.parse(is);
		System.out.println(handler.getProdutos());
		
		
	}

}
