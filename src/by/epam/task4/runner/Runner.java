package by.epam.task4.runner;

import java.util.Set;

import by.epam.task4.entity.Gem;
import by.epam.task4.util.GemConstant;
import by.epam.task4.util.factory.GemBuilderFactory;
import by.epam.task4.util.parser.AbstractGemBuilder;

public class Runner {
	
	public static void main(String[] args) {
		GemBuilderFactory factory = new GemBuilderFactory();
		AbstractGemBuilder builder = factory.createStudentBuilder(GemConstant.DOM);
		builder.buildGemsSet(GemConstant.FILE_PATH);
		Set<Gem> gems = builder.getGems();
		for(Gem gem:gems) {
			System.out.print(gem);
		}
	}
}
