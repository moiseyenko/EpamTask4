package by.epam.task4.util.parser;

import java.util.HashSet;
import java.util.Set;

import by.epam.task4.entity.Gem;

public abstract class AbstractGemBuilder {
	private Set<Gem> gems;

	public AbstractGemBuilder() {
		gems = new HashSet<>();
	}

	public AbstractGemBuilder(Set<Gem> gems) {
		this.gems = gems;
	}

	public Set<Gem> getGems() {
		return gems;
	}

	public abstract void buildGemsSet(String fileName);
}