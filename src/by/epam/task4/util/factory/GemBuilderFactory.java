package by.epam.task4.util.factory;

import by.epam.task4.util.parser.AbstractGemBuilder;
import by.epam.task4.util.parser.dom.DomBuilder;
import by.epam.task4.util.parser.sax.SaxBuilder;
import by.epam.task4.util.parser.stax.StaxBuilder;

public class GemBuilderFactory {
	private enum ParserType{
		SAX,STAX,DOM
	}
	
	//creates a parser(builder) according to parserType;
	public AbstractGemBuilder createStudentBuilder(String parserType) {
		ParserType type = ParserType.valueOf(parserType.toUpperCase());
		switch (type) {
		case DOM:
			return new DomBuilder();
		case SAX:
			return new SaxBuilder();
		case STAX:
			return new StaxBuilder();
		default:
			throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
		}
	}
}
