package by.epam.task4.util.parser.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.task4.entity.FakeGem;
import by.epam.task4.entity.Gem;
import by.epam.task4.entity.PreciousGem;
import by.epam.task4.entity.Price;
import by.epam.task4.entity.VisualParameters;
import by.epam.task4.entity.Weight;
import by.epam.task4.util.GemConstant;
import by.epam.task4.util.parser.AbstractGemBuilder;
import by.epam.task4.util.type.ColorType;
import by.epam.task4.util.type.CurrencyType;
import by.epam.task4.util.type.GemType;
import by.epam.task4.util.type.MethodType;
import by.epam.task4.util.type.PreciousnessType;

public class StaxBuilder extends AbstractGemBuilder {
	private static final Logger LOG = LogManager.getLogger(StaxBuilder.class);
	private XMLInputFactory inputFactory;

	public StaxBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	// build set of gems from fileName
	@Override
	public void buildGemsSet(String fileName) {
		XMLStreamReader reader;
		String name;

		try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (GemType.valueOf(name.toUpperCase()) == GemType.PRECIOUS_GEM) {
						Gem preciousGem = buildPreciousGem(reader);
						getGems().add(preciousGem);
					} else if (GemType.valueOf(name.toUpperCase()) == GemType.FAKE_GEM) {
						Gem fakeGem = buildFakeGem(reader);
						getGems().add(fakeGem);
					}
				}
			}
		} catch (FileNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("File {} not found! {}", fileName, e);
			}
		} catch (XMLStreamException e) {
			if (LOG.isErrorEnabled()) {
				System.out.println(e);
				LOG.error("StAX parsing error! {}", e.getMessage());
			}
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Impossible close file {} : {}", fileName, e);
			}
		}

	}

	// build preciuos gem from reader;
	private Gem buildPreciousGem(XMLStreamReader reader) throws XMLStreamException {
		PreciousGem preciousGem = new PreciousGem();
		preciousGem.setGemId(reader.getAttributeValue(null, GemType.GEM_ID.getValue()));
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (GemType.valueOf(name.toUpperCase())) {
				case NAME:
					preciousGem.setName(getXMLText(reader));
					break;
				case ORIGIN:
					preciousGem.setOrigin(getXMLText(reader));
					break;
				case VISUAL_PARAMETERS:
					VisualParameters visualParameter = buildVisualParameter(reader);
					preciousGem.setVisualParameters(visualParameter);
					break;
				case WEIGHT:
					Weight weight = new Weight();
					weight.setValue(Double.parseDouble(getXMLText(reader)));
					preciousGem.setWeight(weight);
					break;
				case PRICE:
					Price price = new Price();
					String currency = reader.getAttributeValue(null, GemType.CURRENCY.getValue());
					price.setCurrency(CurrencyType.fromValue(currency));
					price.setValue(Double.parseDouble(getXMLText(reader)));
					preciousGem.setPrice(price);
					break;
				case PRECIOUSNESS:
					preciousGem.setPreciousness(PreciousnessType.fromValue(getXMLText(reader)));
					break;
				default:
					throw new XMLStreamException("Unknown element in tag precious_gem");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (GemType.valueOf(name.toUpperCase()) == GemType.PRECIOUS_GEM) {
					return preciousGem;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag precious_gem");
	}

	// build fake gem from reader;
	private Gem buildFakeGem(XMLStreamReader reader) throws XMLStreamException {
		FakeGem fakeGem = new FakeGem();
		fakeGem.setGemId(reader.getAttributeValue(null, GemType.GEM_ID.getValue()));
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (GemType.valueOf(name.toUpperCase())) {
				case NAME:
					fakeGem.setName(getXMLText(reader));
					break;
				case ORIGIN:
					fakeGem.setOrigin(getXMLText(reader));
					break;
				case VISUAL_PARAMETERS:
					VisualParameters visualParameter = buildVisualParameter(reader);
					fakeGem.setVisualParameters(visualParameter);
					break;
				case WEIGHT:
					Weight weight = new Weight();
					weight.setValue(Double.parseDouble(getXMLText(reader)));
					fakeGem.setWeight(weight);
					break;
				case PRICE:
					Price price = new Price();
					String currency = reader.getAttributeValue(null, GemType.CURRENCY.getValue());
					price.setCurrency(CurrencyType.fromValue(currency));
					price.setValue(Double.parseDouble(getXMLText(reader)));
					fakeGem.setPrice(price);
					break;
				case METHOD:
					fakeGem.setMethod(MethodType.fromValue(getXMLText(reader)));
					break;
				case CREATION_DATE:
					String[] splitDate = getXMLText(reader).split(GemConstant.HYPHEN);
					int year = Integer.parseInt(splitDate[0]);
					int month = (Integer.parseInt(splitDate[1]) - 1);
					int days = Integer.parseInt(splitDate[2]);
					fakeGem.setCreationDate(new GregorianCalendar(year, month, days));
					break;
				default:
					throw new XMLStreamException("Unknown element in tag fake_gem");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (GemType.valueOf(name.toUpperCase()) == GemType.FAKE_GEM) {
					return fakeGem;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag fake_gem");
	}

	// return text content from reader;
	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = "";
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}

	// build visual parameters from reader;
	private VisualParameters buildVisualParameter(XMLStreamReader reader) throws XMLStreamException {
		VisualParameters visualParameter = new VisualParameters();
		String faces = reader.getAttributeValue(null, GemType.FACES.getValue());
		if (faces != null) {
			visualParameter.setFaces(Integer.parseInt(faces));
		}
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (GemType.valueOf(name.toUpperCase())) {
				case COLOR:
					visualParameter.setColor(ColorType.fromValue(getXMLText(reader)));
					break;
				case CLARITY:
					visualParameter.setClarity(getXMLText(reader));
					break;
				default:
					throw new XMLStreamException("Unknown element in tag visual_parameters");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (GemType.valueOf(name.toUpperCase()) == GemType.VISUAL_PARAMETERS) {
					return visualParameter;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag visual_parameters");
	}
}