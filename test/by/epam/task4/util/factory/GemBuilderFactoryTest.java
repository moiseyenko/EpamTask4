package by.epam.task4.util.factory;

import org.testng.annotations.Test;

import by.epam.task4.util.GemConstant;
import by.epam.task4.util.parser.AbstractGemBuilder;
import by.epam.task4.util.parser.dom.DomBuilder;
import by.epam.task4.util.parser.sax.SaxBuilder;
import by.epam.task4.util.parser.stax.StaxBuilder;

import static org.testng.Assert.assertEquals;

public class GemBuilderFactoryTest {

	@Test
	public void createStudentBuilderDomTest() {
		GemBuilderFactory factory = new GemBuilderFactory();
		AbstractGemBuilder builder = factory.createStudentBuilder(GemConstant.DOM);
		assertEquals(DomBuilder.class,builder.getClass());
	}
	
	@Test
	public void createStudentBuilderSaxTest() {
		GemBuilderFactory factory = new GemBuilderFactory();
		AbstractGemBuilder builder = factory.createStudentBuilder(GemConstant.SAX);
		assertEquals(SaxBuilder.class,builder.getClass());
	}
	
	@Test
	public void createStudentBuilderStaxTest() {
		GemBuilderFactory factory = new GemBuilderFactory();
		AbstractGemBuilder builder = factory.createStudentBuilder(GemConstant.STAX);
		assertEquals(StaxBuilder.class,builder.getClass());
	}
}
