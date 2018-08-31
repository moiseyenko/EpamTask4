package by.epam.task4.util.parser.sax;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.task4.util.parser.AbstractGemBuilder;

public class SaxBuilder extends AbstractGemBuilder{
	private GemHandler gemHandler;
	private XMLReader reader;

	public SaxBuilder() {
		gemHandler = new GemHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(gemHandler);
		} catch (SAXException e) {
			System.err.println("SAX parser exception" + e);
		}
	}
	
	@Override
	public void buildGemsSet(String fileName) {
		try {
			reader.parse(fileName);
		} catch (IOException e) {
			System.err.println("IO thread exception" + e);
		} catch (SAXException e) {
			System.err.println("SAX parser exception" + e);
		}
		getGems().addAll(gemHandler.getGems());
	}
}
