package by.epam.task4.util.parser.sax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.task4.util.parser.AbstractGemBuilder;

public class SaxBuilder extends AbstractGemBuilder{
	private static final Logger LOG = LogManager.getLogger(SaxBuilder.class);
	private GemHandler gemHandler;
	private XMLReader reader;

	public SaxBuilder() {
		gemHandler = new GemHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(gemHandler);
		} catch (SAXException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("SAX parser exception {}", e);
			}
		}
	}
	
	//build set of gems from fileName;
	@Override
	public void buildGemsSet(String fileName) {
		try {
			reader.parse(fileName);
		} catch (IOException e) {
			LOG.info("IO thread exception {}", e);
			if (LOG.isErrorEnabled()) {
				LOG.error("IO thread exception {}", e);
			}
		} catch (SAXException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("SAX parser exception {}", e);
			}
		}
		getGems().addAll(gemHandler.getGems());
	}
}
