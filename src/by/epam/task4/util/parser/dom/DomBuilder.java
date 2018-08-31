package by.epam.task4.util.parser.dom;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.task4.entity.FakeGem;
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

public class DomBuilder extends AbstractGemBuilder {
	private static final Logger LOG = LogManager.getLogger(DomBuilder.class);
	private DocumentBuilder docBuilder;
	private PreciousGem preciousGem;
	private FakeGem fakeGem;
	private Price price;
	private VisualParameters visualParameters;

	public DomBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("DocumentBuilder creation exception {}", e);
			}
		}
	}

	//build set of gems from fileName;
	@Override
	public void buildGemsSet(String fileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			docBuilder = factory.newDocumentBuilder();
			doc = docBuilder.parse(fileName);
		} catch (ParserConfigurationException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("DocumentBuilder creation exception {}", e);
			}
		}catch (SAXException  e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Parser exception {}", e);
			}
		}catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("IO thread exception {}", e);
			}
		}
		Element root = doc.getDocumentElement();
		parseElement(root);

	}

	//parse current element of dom tree;
	private void parseElement(Node parent) {
		NodeList elementList = parent.getChildNodes();
		for (int i = 0; i < elementList.getLength(); i++) {
			Node node = elementList.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				String tagName = element.getTagName();
				GemType tagNameEnum = GemType.valueOf(tagName.toUpperCase());
				switch (tagNameEnum) {
				case PRECIOUS_GEM:
					preciousGem = new PreciousGem();
					preciousGem.setGemId(element.getAttribute(GemType.GEM_ID.getValue()));
					parseElement(element);
					getGems().add(preciousGem);
					break;
				case FAKE_GEM:
					fakeGem = new FakeGem();
					fakeGem.setGemId(element.getAttribute(GemType.GEM_ID.getValue()));
					parseElement(element);
					getGems().add(fakeGem);
					break;
				case NAME:
					if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.PRECIOUS_GEM) {
						preciousGem.setName(element.getTextContent());
					} else if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.FAKE_GEM) {
						fakeGem.setName(element.getTextContent());
					}
					break;
				case ORIGIN:
					if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.PRECIOUS_GEM) {
						preciousGem.setOrigin(element.getTextContent());
					} else if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.FAKE_GEM) {
						fakeGem.setOrigin(element.getTextContent());
					}
					break;
				case VISUAL_PARAMETERS:
					visualParameters = new VisualParameters();
					if (element.hasAttribute(GemType.FACES.getValue())) {
						visualParameters.setFaces(Integer.parseInt(element.getAttribute(GemType.FACES.getValue())));
					}
					parseElement(element);
					if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.PRECIOUS_GEM) {
						preciousGem.setVisualParameters(visualParameters);
					} else if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.FAKE_GEM) {
						fakeGem.setVisualParameters(visualParameters);
					}
					break;
				case COLOR:
					visualParameters.setColor(ColorType.fromValue(element.getTextContent()));
					break;
				case CLARITY:
					visualParameters.setClarity(element.getTextContent());
					break;
				case WEIGHT:
					Double weightValue = Double.parseDouble(element.getTextContent());
					Weight currentWeight = new Weight();
					currentWeight.setValue(weightValue);
					if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.PRECIOUS_GEM) {
						preciousGem.setWeight(currentWeight);
					} else if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.FAKE_GEM) {
						fakeGem.setWeight(currentWeight);
					}
					break;
				case PRICE:
					price = new Price();
					price.setCurrency(CurrencyType.fromValue(element.getAttribute(GemType.CURRENCY.getValue())));
					Double priceValue = Double.parseDouble(element.getTextContent());
					price.setValue(priceValue);
					if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.PRECIOUS_GEM) {
						preciousGem.setPrice(price);
					} else if (GemType.valueOf(parent.getNodeName().toUpperCase()) == GemType.FAKE_GEM) {
						fakeGem.setPrice(price);
					}
					break;
				case PRECIOUSNESS:
					preciousGem.setPreciousness(PreciousnessType.fromValue(element.getTextContent()));
					break;
				case METHOD:
					fakeGem.setMethod(MethodType.fromValue(element.getTextContent()));
					break;
				case CREATION_DATE:
					String date = element.getTextContent();
					String[] splitDate = date.split(GemConstant.HYPHEN);
					int year = Integer.parseInt(splitDate[0]);
					int month = (Integer.parseInt(splitDate[1]) - 1);
					int days = Integer.parseInt(splitDate[2]);
					fakeGem.setCreationDate(new GregorianCalendar(year, month, days));
					break;
				default:
					throw new EnumConstantNotPresentException(tagNameEnum.getDeclaringClass(), tagNameEnum.name());
				}
			}
		}
	}

}
