package by.epam.task4.validator;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MyErrorHandler extends DefaultHandler{
	/*org.apache.logging.log4j.Logger logger
    = org.apache.logging.log4j.LogManager.getLogger("loggerNameFromXMLConfig");
  org.apache.logging.log4j.core.Logger coreLogger
    = (org.apache.logging.log4j.core.Logger)logger;
  org.apache.logging.log4j.core.LoggerContext context
    = (org.apache.logging.log4j.core.LoggerContext)coreLogger.getContext();
  org.apache.logging.log4j.core.config.BaseConfiguration configuration
    = (org.apache.logging.log4j.core.config.BaseConfiguration)context.getConfiguration();
  coreLogger.addAppender(configuration.getAppender("appenderNameFromXMLConfig"));*/
	private Logger LOG = LogManager.getLogger("EpamTask4");
	
	public MyErrorHandler() throws FileNotFoundException, IOException {
	
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		LOG.warn(getLineAddress(e)+ "-"+e.getMessage());
	}
	
	public void error(SAXParseException e) throws SAXException {
		LOG.error(getLineAddress(e)+ "-"+e.getMessage());
	}
	
	public void fatal(SAXParseException e) throws SAXException {
		LOG.fatal(getLineAddress(e)+ "-"+e.getMessage());
	}
	
	private String  getLineAddress (SAXParseException e) {
		return e.getLineNumber() +" : "+ e.getColumnNumber();
	}
	
}
