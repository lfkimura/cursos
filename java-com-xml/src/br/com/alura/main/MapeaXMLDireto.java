package br.com.alura.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.alura.model.Produto;
import br.com.alura.model.Venda;

public class MapeaXMLDireto {
	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);
//		xmlParaObjeto(jaxbContext);
		objetoParaXml(jaxbContext);
	}

	private static void objetoParaXml(JAXBContext jaxbContext) throws JAXBException, IOException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		// TODO Auto-generated method stub
		Venda venda = new Venda();
		venda.setFormaDePagamento("Crediario");
		List<Produto> produtos= new ArrayList<>();
		produtos.add(new Produto("livro de java", 20.00));
		produtos.add(new Produto("livro de c++", 21.00));
		produtos.add(new Produto("livro de XML", 22.00));
		venda.setProdutos(produtos);
		
		
		StringWriter writer = new StringWriter();
		marshaller.marshal(venda, writer);
		System.out.println(writer.toString());
		FileWriter fw = new FileWriter("src/vendaOut.xml");
		fw.write(writer.toString());
		fw.close();
		
	}

	private static void xmlParaObjeto(JAXBContext jaxbContext) throws JAXBException {
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Venda venda = (Venda)unmarshaller.unmarshal(new File("src/venda.xml"));
		System.out.println(venda);
	}

}
