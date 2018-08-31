package by.epam.task4.util.parser;

import static org.testng.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.task4.entity.FakeGem;
import by.epam.task4.entity.Gem;
import by.epam.task4.entity.PreciousGem;
import by.epam.task4.entity.Price;
import by.epam.task4.entity.VisualParameters;
import by.epam.task4.entity.Weight;
import by.epam.task4.util.parser.dom.DomBuilder;
import by.epam.task4.util.parser.sax.SaxBuilder;
import by.epam.task4.util.parser.stax.StaxBuilder;
import by.epam.task4.util.type.ColorType;
import by.epam.task4.util.type.CurrencyType;
import by.epam.task4.util.type.MethodType;
import by.epam.task4.util.type.PreciousnessType;

public class BuilderTest {

	Set<Gem> gems;

	@BeforeClass
	public void setUp() {
		gems = new HashSet<>();
		PreciousGem preciousGem = new PreciousGem();
		preciousGem.setGemId("a1");
		preciousGem.setName("Diamond");
		preciousGem.setOrigin("USA");
		VisualParameters preciousVisualParameters = new VisualParameters();
		preciousVisualParameters.setFaces(57);
		preciousVisualParameters.setColor(ColorType.COLORLESS);
		preciousVisualParameters.setClarity("98%");
		preciousGem.setVisualParameters(preciousVisualParameters);
		Weight preciousWeight = new Weight();
		preciousWeight.setValue(1.01);
		preciousGem.setWeight(preciousWeight);
		Price preciousPrice = new Price();
		preciousPrice.setCurrency(CurrencyType.USD);
		preciousPrice.setValue(4000000);
		preciousGem.setPrice(preciousPrice);
		preciousGem.setPreciousness(PreciousnessType.PRECIOUS);
		gems.add(preciousGem);

		FakeGem fakeGem = new FakeGem();
		fakeGem.setGemId("b1");
		fakeGem.setName("Synthetic diamond");
		fakeGem.setOrigin("China");
		VisualParameters fakeVisualParameters = new VisualParameters();
		fakeVisualParameters.setFaces(57);
		fakeVisualParameters.setColor(ColorType.COLORLESS);
		fakeVisualParameters.setClarity("90%");
		fakeGem.setVisualParameters(fakeVisualParameters);
		Weight fakeWeight = new Weight();
		fakeWeight.setValue(1.01);
		fakeGem.setWeight(fakeWeight);
		Price fakePrice = new Price();
		fakePrice.setCurrency(CurrencyType.USD);
		fakePrice.setValue(200);
		fakeGem.setPrice(fakePrice);
		fakeGem.setMethod(MethodType.CRYSTALLIZATION);
		fakeGem.setCreationDate(new GregorianCalendar(2017, 5, 8));
		gems.add(fakeGem);
	}

	@Test(dataProvider = "dp" )
	public void domBuildGemsSetTest(String fileName) {
		DomBuilder domBuilder = new DomBuilder();
		domBuilder.buildGemsSet(fileName);
		assertEquals(domBuilder.getGems(), gems, "not equals!");
	}
	
	@Test(dataProvider = "dp" )
	public void saxBuildGemsSetTest(String fileName) {
		SaxBuilder saxBuilder = new SaxBuilder();
		saxBuilder.buildGemsSet(fileName);
		assertEquals(saxBuilder.getGems(), gems, "not equals!");
	}
	
	@Test(dataProvider = "dp" )
	public void staxBuildGemsSetTest(String fileName) {
		StaxBuilder staxBuilder = new StaxBuilder();
		staxBuilder.buildGemsSet(fileName);
		assertEquals(staxBuilder.getGems(), gems, "not equals!");
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { { "gemsTestValid.xml" } };
	}
}
