package by.epam.task4.entity;

import by.epam.task4.util.type.PreciousnessType;

public class PreciousGem extends Gem {

	private PreciousnessType preciousness;

	public PreciousnessType getPreciousness() {
		return preciousness;
	}

	public void setPreciousness(PreciousnessType value) {
		this.preciousness = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PreciousGem preciousGem = (PreciousGem) obj;
		if (preciousness != preciousGem.preciousness) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((preciousness == null) ? 0 : preciousness.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", preciousness=" + preciousness + "\n";
	}

}
