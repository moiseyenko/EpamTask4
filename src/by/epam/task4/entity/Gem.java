package by.epam.task4.entity;

public abstract class Gem {

	private String name;
	private String origin;
	private VisualParameters visualParameters;
	private Weight weight;
	private Price price;
	private String gemId;

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String value) {
		this.origin = value;
	}

	public VisualParameters getVisualParameters() {
		return visualParameters;
	}

	public void setVisualParameters(VisualParameters value) {
		this.visualParameters = value;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price value) {
		this.price = value;
	}

	public String getGemId() {
		return gemId;
	}

	public void setGemId(String value) {
		this.gemId = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Gem gem = (Gem) obj;
		if (name == null) {
			if (gem.name != null) {
				return false;
			}
		} else if (!name.equals(gem.name)) {
			return false;
		}
		if (origin == null) {
			if (gem.origin != null) {
				return false;
			}
		} else if (!origin.equals(gem.origin)) {
			return false;
		}
		if (visualParameters == null) {
			if (gem.visualParameters != null) {
				return false;
			}
		} else if (!visualParameters.equals(gem.visualParameters)) {
			return false;
		}
		if (weight == null) {
			if (gem.weight != null) {
				return false;
			}
		} else if (!weight.equals(gem.weight)) {
			return false;
		}
		if (price == null) {
			if (gem.price != null) {
				return false;
			}
		} else if (!price.equals(gem.price)) {
			return false;
		}
		if (gemId == null) {
			if (gem.gemId != null) {
				return false;
			}
		} else if (!gemId.equals(gem.gemId)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gemId == null) ? 0 : gemId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((visualParameters == null) ? 0 : visualParameters.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": gemId=" + gemId + ", name=" + name + ", origin=" + origin + ", "
				+ visualParameters + ", weight=" + weight + ", price=" + price;
	}

}
