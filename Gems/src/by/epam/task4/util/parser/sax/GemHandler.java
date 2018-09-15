package by.epam.task4.util.parser.sax;

import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.task4.entity.FakeGem;
import by.epam.task4.entity.Gem;
import by.epam.task4.entity.PreciousGem;
import by.epam.task4.entity.Price;
import by.epam.task4.entity.VisualParameters;
import by.epam.task4.entity.Weight;
import by.epam.task4.util.GemConstant;
import by.epam.task4.util.type.ColorType;
import by.epam.task4.util.type.CurrencyType;
import by.epam.task4.util.type.GemType;
import by.epam.task4.util.type.MethodType;
import by.epam.task4.util.type.PreciousnessType;

public class GemHandler extends DefaultHandler {

	private Set<Gem> gems;
	private Gem current;
	private VisualParameters visualParameters;
	private Price price;
	private GemType currentEnum;
	private EnumSet<GemType> withText;

	public GemHandler() {
		gems = new HashSet<>();
		withText = EnumSet.range(GemType.NAME, GemType.CREATION_DATE);
	}

	public Set<Gem> getGems() {
		return gems;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (GemType.valueOf(qName.toUpperCase()) == GemType.PRECIOUS_GEM) {
			current = new PreciousGem();
			current.setGemId(attributes.getValue(GemType.GEM_ID.getValue()));
		} else if (GemType.valueOf(qName.toUpperCase()) == GemType.FAKE_GEM) {
			current = new FakeGem();
			current.setGemId(attributes.getValue(GemType.GEM_ID.getValue()));
		} else if (GemType.valueOf(qName.toUpperCase()) == GemType.VISUAL_PARAMETERS) {
			visualParameters = new VisualParameters();
			if (attributes.getValue(GemType.FACES.getValue()) != null) {
				visualParameters.setFaces(Integer.parseInt(attributes.getValue(GemType.FACES.getValue())));
			}
		} else if (GemType.valueOf(qName.toUpperCase()) == GemType.PRICE) {
			price = new Price();
			price.setCurrency(CurrencyType.fromValue(attributes.getValue(GemType.CURRENCY.getValue())));
			currentEnum = GemType.valueOf(qName.toUpperCase());
		} else {
			GemType temp = GemType.valueOf(qName.toUpperCase());
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (GemType.PRECIOUS_GEM.getValue().equals(localName)||(GemType.FAKE_GEM.getValue().equals(localName))) {
			gems.add(current);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case NAME:
				current.setName(s);
				break;
			case ORIGIN:
				current.setOrigin(s);
				break;
			case COLOR:
				visualParameters.setColor(ColorType.fromValue(s));
				break;
			case CLARITY:
				visualParameters.setClarity(s);
				current.setVisualParameters(visualParameters);
				break;
			case WEIGHT:
				Weight weight = new Weight();
				weight.setValue(Double.parseDouble(s));
				current.setWeight(weight);
				break;
			case PRICE:
				double temp = Double.parseDouble(s);
				price.setValue(temp);
				current.setPrice(price);
				break;
			case PRECIOUSNESS:
				PreciousGem currentPreciuos = (PreciousGem) current;
				currentPreciuos.setPreciousness(PreciousnessType.fromValue(s));
				break;
			case METHOD:
				FakeGem currentFake = (FakeGem) current;
				currentFake.setMethod(MethodType.fromValue(s));
				break;
			case CREATION_DATE:
				String[] splitDate = s.split(GemConstant.HYPHEN);
				int year = Integer.parseInt(splitDate[0]);
				int month = (Integer.parseInt(splitDate[1]) - 1);
				int days = Integer.parseInt(splitDate[2]);
				FakeGem curentFake = (FakeGem) current;
				curentFake.setCreationDate(new GregorianCalendar(year, month, days));
				break;
			default:
				throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;

	}

}
