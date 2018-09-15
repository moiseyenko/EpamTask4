package by.epam.task4.runner;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.task4.entity.Gem;
import by.epam.task4.util.GemConstant;
import by.epam.task4.util.factory.GemBuilderFactory;
import by.epam.task4.util.parser.AbstractGemBuilder;

public class Runner {
	private final static Logger LOG = LogManager.getLogger(Runner.class);
	public static void main(String[] args) {
		GemBuilderFactory factory = new GemBuilderFactory();
		AbstractGemBuilder builder = factory.createBuilder(GemConstant.STAX);
		builder.buildGemsSet(GemConstant.FILE_PATH);
		Set<Gem> gems = builder.getGems();
		if (LOG.isInfoEnabled()) {
			for(Gem gem:gems) {
				LOG.info(gem);
			}
		}
	}
}
