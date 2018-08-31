package by.epam.task4.validator;
import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class MyValidator {
	public static void main(String[] args) {
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		String fileName = "gems.xml";
		String schemaName = "gems.xsd";
		SchemaFactory factory = SchemaFactory.newInstance(language);
		try {
			Schema schema = factory.newSchema(new File(schemaName));
			Validator validator = schema.newValidator();
			MyErrorHandler meh = new MyErrorHandler();
			validator.setErrorHandler(meh);
			validator.validate(new StreamSource(fileName));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
